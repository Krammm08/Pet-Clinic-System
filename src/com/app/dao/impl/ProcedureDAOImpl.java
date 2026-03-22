package com.app.dao.impl;

import com.app.dao.ProcedureDAO;
import com.app.model.Procedure;
import com.app.util.DbConnection; // Using your connection utility

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        List<Procedure> procedureList = new ArrayList<>();
        String sql = "SELECT * FROM tblprocedures";

        try (Connection conn = DbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Procedure procedure = new Procedure();

                procedure.setProcedureId(rs.getInt("procedure_id"));
                procedure.setAppointmentId(rs.getInt("appointment_id"));
                procedure.setServiceId(rs.getInt("service_id"));
                procedure.setVetId(rs.getInt("vet_id"));
                procedure.setUserId(rs.getInt("user_id"));
                procedure.setPetId(rs.getInt("pet_id"));
                procedure.setDiagnosis(rs.getString("diagnosis"));
                procedure.setMedicineId(rs.getInt("medicine_id"));

                // Read the date from the database as a String
                procedure.setProcedureDate(rs.getString("procedure_date"));

                procedureList.add(procedure);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving procedures: " + e.getMessage());
        }
        return procedureList;
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
}