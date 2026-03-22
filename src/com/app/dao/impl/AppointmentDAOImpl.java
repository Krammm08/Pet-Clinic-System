package com.app.dao.impl;

import com.app.dao.AppointmentDAO;
import com.app.model.Appointment;
import com.app.util.DbConnection;
import com.app.exception.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAOImpl implements AppointmentDAO {

    @Override
    public boolean insertAppointment(Appointment appointment) throws DatabaseException {
        // We skip appointment_id as it is AUTO_INCREMENT
        String sql = "INSERT INTO tblappointments (user_id, pet_id, service_id, appointment_date, appointment_time, is_approve) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, appointment.getUserID());
            ps.setInt(2, appointment.getPetID());
            ps.setInt(3, appointment.getServiceID());
            ps.setDate(4, appointment.getAppointmentDate());
            ps.setTime(5, appointment.getAppointmentTime());

            // Usually starts as 0 (Pending) when first created
            ps.setInt(6, appointment.getIsApprove());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            // Throwing your custom exception instead of just printing it!
            throw new DatabaseException("Failed to insert appointment: " + e.getMessage());
        }
    }

    @Override
    public List<Appointment> getUserAppointments(int userId) {
        List<Appointment> apptList = new ArrayList<>();
        String sql = "SELECT * FROM tblappointments WHERE user_id = ?";

        try (Connection conn = DbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Appointment appt = new Appointment();
                    appt.setAppointmentID(rs.getInt("appointment_id"));
                    appt.setUserID(rs.getInt("user_id"));
                    appt.setPetID(rs.getInt("pet_id"));
                    appt.setServiceId(rs.getInt("service_id"));
                    appt.setAppointmentDate(rs.getDate("appointment_date"));
                    appt.setAppointmentTime(rs.getTime("appointment_time"));
                    appt.setIsApprove(rs.getInt("is_approve"));
                    apptList.add(appt);
                }
            }
        } catch (Exception e) {
            System.out.println("Error retrieving user appointments: " + e.getMessage());
        }
        return apptList;
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
        List<Appointment> appointmentList = new ArrayList<>();
        String sql = "SELECT * FROM tblappointments";

        try (Connection conn = DbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Appointment appointment = extractAppointmentFromResultSet(rs);
                appointmentList.add(appointment);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Failed to retrieve all appointments: " + e.getMessage());
        }
        return appointmentList;
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

    // --- HELPER METHOD ---
    // Instead of copying and pasting the ResultSet extraction 3 times,
    // a private helper method keeps your code clean and professional!
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
}