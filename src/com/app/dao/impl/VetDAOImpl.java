package com.app.dao.impl;

import com.app.dao.VetDAO;
import com.app.model.Vet;
import com.app.util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VetDAOImpl implements VetDAO {

    @Override
    public boolean addVet(Vet vet) {
        // Skipping vet_id so MySQL can auto-increment it
        String sql = "INSERT INTO tblvets (vet_name, last_name, first_name, age, gender, contact_number, email_address, specialization) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, vet.getVetName());
            ps.setString(2, vet.getLastName());
            ps.setString(3, vet.getFirstName());
            ps.setInt(4, vet.getAge());
            ps.setString(5, vet.getGender());
            ps.setString(6, vet.getContactNumber());
            ps.setString(7, vet.getEmailAddress());
            ps.setString(8, vet.getSpecialization());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error adding vet: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Vet> getAllVets() {
        List<Vet> vetList = new ArrayList<>();
        String sql = "SELECT * FROM tblvets";

        try (Connection conn = DbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Vet vet = new Vet();

                vet.setVetId(rs.getInt("vet_id"));
                // Note: If vet_name is a combination of first and last name, make sure the column exists in your DB!
                vet.setVetName(rs.getString("vet_name"));
                vet.setLastName(rs.getString("last_name"));
                vet.setFirstName(rs.getString("first_name"));
                vet.setAge(rs.getInt("age"));
                vet.setGender(rs.getString("gender"));
                vet.setContactNumber(rs.getString("contact_number"));
                vet.setEmailAddress(rs.getString("email_address"));
                vet.setSpecialization(rs.getString("specialization"));

                vetList.add(vet);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving vets: " + e.getMessage());
        }
        return vetList;
    }

    @Override
    public Vet getVetById(int vetId) {
        return null; // TODO: Implement later
    }

    @Override
    public boolean updateVet(Vet vet) {
        return false; // TODO: Implement later
    }

    @Override
    public boolean deleteVet(int vetId) {
        return false; // TODO: Implement later
    }
}