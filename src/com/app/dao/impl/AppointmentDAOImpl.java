package com.app.dao.impl;

import com.app.dao.AppointmentDAO;
import com.app.model.Appointment;
import com.app.util.DbConnection;
import com.app.exception.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAOImpl implements AppointmentDAO {

    @Override
    public void insertAppointment(Appointment appointment) throws DatabaseException {
        String sqlApp = "INSERT INTO tblappointments (pet_id, service_id, user_id, appointment_date, appointment_time, is_approve) VALUES (?, ?, ?, ?, ?, 0)";
        String sqlProc = "INSERT INTO tblprocedures (appointment_id, service_id, vet_id, user_id, pet_id, diagnosis, medicine_id, procedure_date) VALUES (?, ?, 1, ?, ?, 'Pending Exam', 1, ?)";
        String sqlTrans = "INSERT INTO tbltransactions (user_id, service_id, procedure_id, medicine_id, quantity, total_amount, is_paid) VALUES (?, ?, ?, 1, 0, ?, 0)";

        // NEW: SQL to get the actual service price
        String sqlPrice = "SELECT service_fee FROM tblofferedservices WHERE service_id = ?";

        Connection conn = null;
        try {
            conn = DbConnection.connect();
            conn.setAutoCommit(false);

            int generatedAppId = 0, generatedProcId = 0;
            double actualPrice = 0.0; // Variable to hold the real price

            // --- NEW STEP: FETCH REAL PRICE ---
            try (PreparedStatement stmtPrice = conn.prepareStatement(sqlPrice)) {
                stmtPrice.setInt(1, appointment.getServiceID());
                ResultSet rsPrice = stmtPrice.executeQuery();
                if (rsPrice.next()) {
                    actualPrice = rsPrice.getDouble("service_fee");
                } else {
                    actualPrice = 500.0; // Fallback just in case
                }
            }

            // --- STEP 1: APPOINTMENT ---
            try (PreparedStatement stmtApp = conn.prepareStatement(sqlApp, Statement.RETURN_GENERATED_KEYS)) {
                stmtApp.setInt(1, appointment.getPetID());
                stmtApp.setInt(2, appointment.getServiceID());
                stmtApp.setInt(3, appointment.getUserID());
                stmtApp.setDate(4, appointment.getAppointmentDate());
                stmtApp.setTime(5, appointment.getAppointmentTime());
                stmtApp.executeUpdate();
                ResultSet rs = stmtApp.getGeneratedKeys();
                if (rs.next()) generatedAppId = rs.getInt(1);
            }

            // --- STEP 2: PROCEDURE ---
            try (PreparedStatement stmtProc = conn.prepareStatement(sqlProc, Statement.RETURN_GENERATED_KEYS)) {
                stmtProc.setInt(1, generatedAppId);
                stmtProc.setInt(2, appointment.getServiceID());
                stmtProc.setInt(3, appointment.getUserID());
                stmtProc.setInt(4, appointment.getPetID());
                stmtProc.setDate(5, appointment.getAppointmentDate());
                stmtProc.executeUpdate();
                ResultSet rs = stmtProc.getGeneratedKeys();
                if (rs.next()) generatedProcId = rs.getInt(1);
            }

            // --- STEP 3: TRANSACTION ---
            try (PreparedStatement stmtTrans = conn.prepareStatement(sqlTrans)) {
                stmtTrans.setInt(1, appointment.getUserID());
                stmtTrans.setInt(2, appointment.getServiceID());
                stmtTrans.setInt(3, generatedProcId);
                stmtTrans.setDouble(4, actualPrice); // Uses the real price now!
                stmtTrans.executeUpdate();
            }

            conn.commit();

        } catch (SQLException e) {
            if (conn != null) try { conn.rollback(); } catch (SQLException ex) { }
            throw new DatabaseException("Failed to automate transaction: " + e.getMessage());
        }
    }
    @Override
    public List<Appointment> getAppointmentsByUserId(int userId) throws DatabaseException {
        List<Appointment> appointmentList = new ArrayList<>();
        // Targeted query using WHERE clause
        String sql = "SELECT * FROM tblappointments WHERE user_id = ?";

        try (Connection conn = DbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Appointment appointment = extractAppointmentFromResultSet(rs);
                    appointmentList.add(appointment);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Failed to retrieve appointments for user ID " + userId + ": " + e.getMessage());
        }
        return appointmentList;
    }

    @Override
    public List<Appointment> getAllAppointments() throws DatabaseException {
        List<Appointment> list = new ArrayList<>();

        // --- THE INNER JOIN MEGA-QUERY ---
        // 'a' is tblappointments, 'u' is tblusers, 'p' is tblpets, 's' is tblofferedservices
// 1. UPDATE THE SQL STRING (Change to s.service_type)
        String sql = "SELECT a.appointment_id, a.appointment_date, a.appointment_time, a.is_approve, " +
                "u.username, p.pet_name, s.service_type " +
                "FROM tblappointments a " +
                "INNER JOIN tblusers u ON a.user_id = u.user_id " +
                "INNER JOIN tblpets p ON a.pet_id = p.pet_id " +
                "INNER JOIN tblofferedservices s ON a.service_id = s.service_id";

        try (Connection conn = DbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Appointment app = new Appointment();
                app.setAppointmentID(rs.getInt("appointment_id"));
                app.setAppointmentDate(rs.getDate("appointment_date"));
                app.setAppointmentTime(rs.getTime("appointment_time"));
                app.setIsApprove(rs.getInt("is_approve"));

                app.setOwnerName(rs.getString("username"));
                app.setPetName(rs.getString("pet_name"));

                // 2. UPDATE THE FETCH LINE (Change to service_type)
                app.setServiceName(rs.getString("service_type"));

                list.add(app);
            }
            // ... rest of method ...
        } catch (SQLException e) {
            System.out.println("X Admin Fetch Error: " + e.getMessage());
        }
        return list;
    }

    @Override
    public Appointment getAppointmentById(int appointmentId) throws DatabaseException {
        String sql = "SELECT * FROM tblappointments WHERE appointment_id = ?";
        Appointment appointment = null;

        try (Connection conn = DbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, appointmentId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    appointment = extractAppointmentFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Failed to retrieve appointment ID " + appointmentId + ": " + e.getMessage());
        }
        return appointment;
    }

    @Override
    public boolean approveAppointment(int appointmentId) throws DatabaseException {
        // Specifically updates is_approve to 1 (Approved)
        String sql = "UPDATE tblappointments SET is_approve = 1 WHERE appointment_id = ?";

        try (Connection conn = DbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, appointmentId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DatabaseException("Failed to approve appointment ID " + appointmentId + ": " + e.getMessage());
        }
    }

    @Override
    public boolean declineAppointment(int appointmentId) throws DatabaseException {
        // Specifically updates is_approve to 2 (Declined) so we don't lose the record
        String sql = "UPDATE tblappointments SET is_approve = 2 WHERE appointment_id = ?";

        try (Connection conn = DbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, appointmentId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DatabaseException("Failed to decline appointment ID " + appointmentId + ": " + e.getMessage());
        }
    }

    private Appointment extractAppointmentFromResultSet(ResultSet rs) throws SQLException {
        Appointment appointment = new Appointment();
        appointment.setAppointmentID(rs.getInt("appointment_id"));
        appointment.setUserID(rs.getInt("user_id"));
        appointment.setPetID(rs.getInt("pet_id"));
        appointment.setServiceId(rs.getInt("service_id"));
        appointment.setAppointmentDate(rs.getDate("appointment_date"));
        appointment.setAppointmentTime(rs.getTime("appointment_time"));
        appointment.setIsApprove(rs.getInt("is_approve"));
        return appointment;
    }
    @Override
    public boolean updateAppointmentStatus(int appointmentId, String status) throws DatabaseException {
        // Notice I am guessing your column is named 'is_approve' based on your getter method.
        // If your database column is named 'status', change 'is_approve' to 'status' in the SQL string!
        String sql = "UPDATE tblappointments SET is_approve = ? WHERE appointment_id = ?";

        try (Connection conn = DbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setInt(2, appointmentId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Returns true if the database was successfully updated

        } catch (SQLException e) {
            throw new DatabaseException("Error updating appointment status: " + e.getMessage());
        }
    }
}