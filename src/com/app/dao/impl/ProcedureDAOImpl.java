package com.app.dao.impl;

import com.app.dao.ProcedureDAO;
import com.app.model.Procedure;
import com.app.util.DbConnection; // Using your connection utility

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProcedureDAOImpl implements ProcedureDAO {

    @Override
    public boolean addProcedure(Procedure procedure) {
        // We skip procedure_id (auto-increment) and procedure_date (usually auto-timestamped by the database)
        String sql = "INSERT INTO tblprocedures (appointment_id, service_id, vet_id, user_id, pet_id, diagnosis, medicine_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, procedure.getAppointmentId());
            ps.setInt(2, procedure.getServiceId());
            ps.setInt(3, procedure.getVetId());
            ps.setInt(4, procedure.getUserId());
            ps.setInt(5, procedure.getPetId());
            ps.setString(6, procedure.getDiagnosis());
            ps.setInt(7, procedure.getMedicineId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error adding procedure: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Procedure> getAllProcedures() {
        List<Procedure> list = new ArrayList<>();

        // --- THE MEGA JOIN SQL ---
        // Joins 6 tables and filters for Approved Appointments (a.is_approve = 1)
        String sql = "SELECT p.procedure_id, p.diagnosis, p.procedure_date, " +
                "u.username, pt.pet_name, v.vet_name, s.service_type, m.med_name " +
                "FROM tblprocedures p " +
                "INNER JOIN tblappointments a ON p.appointment_id = a.appointment_id " +
                "INNER JOIN tblusers u ON p.user_id = u.user_id " +
                "INNER JOIN tblpets pt ON p.pet_id = pt.pet_id " +
                "INNER JOIN tblvets v ON p.vet_id = v.vet_id " +
                "INNER JOIN tblofferedservices s ON p.service_id = s.service_id " +
                "INNER JOIN tblmedicines m ON p.medicine_id = m.medicine_id " +
                "WHERE a.is_approve = 1";

        try (Connection conn = DbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Procedure p = new Procedure();

                // We only need the Procedure ID for the system to work
                p.setProcedureId(rs.getInt("procedure_id"));
                p.setDiagnosis(rs.getString("diagnosis"));
                p.setProcedureDate(rs.getString("procedure_date"));

                // --- THE BEAUTIFUL TEXT DATA ---
                p.setOwnerName(rs.getString("username"));
                p.setPetName(rs.getString("pet_name"));
                p.setVetName(rs.getString("vet_name"));
                p.setServiceName(rs.getString("service_type"));
                p.setMedicineName(rs.getString("med_name"));

                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("\tX Error fetching procedures: " + e.getMessage());
        }
        return list;
    }

    @Override
    public Procedure getProcedureById(int procedureId) {
        return null; // TODO: Implement later
    }

    @Override
    public boolean updateProcedure(Procedure procedure) {
        return false; // TODO: Implement later
    }

    @Override
    public boolean deleteProcedure(int procedureId) {
        return false; // TODO: Implement later
    }
    public boolean updateProcedureAndBill(int procedureId, String diagnosis, int medicineId) {
        // The 4 SQL Commands we need to run together
        String sqlCheckMed = "SELECT cost, inventory_count FROM tblmedicines WHERE medicine_id = ?";
        String sqlUpdateProc = "UPDATE tblprocedures SET diagnosis = ?, medicine_id = ? WHERE procedure_id = ?";
        String sqlUpdateTrans = "UPDATE tbltransactions SET total_amount = total_amount + ? WHERE procedure_id = ?";
        String sqlUpdateMed = "UPDATE tblmedicines SET inventory_count = inventory_count - 1 WHERE medicine_id = ?";

        Connection conn = null;
        try {
            conn = DbConnection.connect();
            conn.setAutoCommit(false); // START TRANSACTION: If one fails, they all fail!

            double medCost = 0.0;
            int stock = 0;

            // 1. CHECK MEDICINE INVENTORY & PRICE (Skip if ID is 1 "No Medicine")
            if (medicineId != 1) {
                try (PreparedStatement stmtCheck = conn.prepareStatement(sqlCheckMed)) {
                    stmtCheck.setInt(1, medicineId);
                    ResultSet rs = stmtCheck.executeQuery();
                    if (rs.next()) {
                        medCost = rs.getDouble("cost");
                        stock = rs.getInt("inventory_count");
                    } else {
                        throw new Exception("Medicine ID not found in database.");
                    }
                }
                if (stock <= 0) {
                    throw new Exception("This medicine is currently out of stock!");
                }
            }

            // 2. UPDATE THE DIAGNOSIS
            try (PreparedStatement stmtProc = conn.prepareStatement(sqlUpdateProc)) {
                stmtProc.setString(1, diagnosis);
                stmtProc.setInt(2, medicineId);
                stmtProc.setInt(3, procedureId);
                int rows = stmtProc.executeUpdate();
                if (rows == 0) throw new Exception("Procedure ID not found!");
            }

            // 3. UPDATE THE CUSTOMER'S BILL & INVENTORY (Only if a medicine was given)
            if (medicineId != 1 && medCost > 0) {
                // Add price to bill
                try (PreparedStatement stmtTrans = conn.prepareStatement(sqlUpdateTrans)) {
                    stmtTrans.setDouble(1, medCost);
                    stmtTrans.setInt(2, procedureId);
                    stmtTrans.executeUpdate();
                }
                // Deduct 1 from stock
                try (PreparedStatement stmtMed = conn.prepareStatement(sqlUpdateMed)) {
                    stmtMed.setInt(1, medicineId);
                    stmtMed.executeUpdate();
                }
            }

            conn.commit(); // SAVE EVERYTHING!
            return true;

        } catch (Exception e) {
            // IF ANYTHING FAILS, CANCEL THE WHOLE OPERATION
            if (conn != null) try { conn.rollback(); } catch (SQLException ex) { }
            System.out.println("\tX Diagnosis Failed: " + e.getMessage());
            return false;
        } finally {
            if (conn != null) try { conn.setAutoCommit(true); conn.close(); } catch (SQLException ex) { }
        }
    }

}