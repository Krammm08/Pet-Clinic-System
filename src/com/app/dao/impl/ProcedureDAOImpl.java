package com.app.dao.impl;

import com.app.dao.ProcedureDAO;
import com.app.model.Procedure;
import com.app.util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProcedureDAOImpl implements ProcedureDAO {

    @Override
    public boolean addProcedure(Procedure procedure) {
        String sql = "INSERT INTO tblprocedures (procedure_id, appointment_id, service_id, vet_id, user_id, pet_id, diagnosis, medicine_id, procedure_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection connection = DbConnection.connect();
            PreparedStatement prep = connection.prepareStatement(sql)){

            prep.setInt(1, procedure.getAppointmentId());
            prep.setInt(2, procedure.getServiceId());
            prep.setInt(3, procedure.getServiceId());
            prep.setInt(4, procedure.getUserId());
            prep.setInt(5, procedure.getPetId());
            prep.setString(6, procedure.getDiagnosis());

            prep.setInt(7, procedure.getMedicineId());

            return prep.executeUpdate() > 0;

        } catch (Exception e){
            System.out.println("Error adding medicine: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Procedure> getAllProcedure() {
        List<Procedure> procedureList = new ArrayList<>();
        String sql = "SELECT * FROM tblprocedures";

        try (Connection conn = DbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Procedure procedure = new Procedure();

                // Retrieving all data from the database
                procedure.setProcedureId(rs.getInt("procedure_id"));
                procedure.setAppointmentId(rs.getInt("appointment_id"));
                procedure.setServiceId(rs.getInt("service_id"));
                procedure.setVetId(rs.getInt("vet_id"));
                procedure.setUserId(rs.getInt("user_id"));
                procedure.setPetId(rs.getInt("pet_id"));
                procedure.setDiagnosis(rs.getString("diagnosis"));
                procedure.setMedicineId(rs.getInt("medicine_id"));
                procedure.setProcedureDate(rs.getDate("procedure_date"));

                procedureList.add(procedure);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving procedure records: " + e.getMessage());
        }
        return procedureList;
    }

    @Override
    public Procedure getProcedureById(int procedureId) {
        return null;
    }

    @Override
    public boolean updateProcedure(Procedure procedure) {
        return false;
    }

    @Override
    public boolean deleteProcedure(int id) {
        return false;
    }
}
