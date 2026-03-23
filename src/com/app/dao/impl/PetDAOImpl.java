package com.app.dao.impl;

import com.app.dao.PetDAO;
import com.app.model.Pet;
import com.app.util.DbConnection; // Using your connection utility

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetDAOImpl implements PetDAO {

    @Override
    public boolean addPet(Pet pet) {
        // Skipping pet_id because MySQL auto-increments it
        String sql = "INSERT INTO tblpets (pet_name, animal_type, breed, age, gender, weights_kg, user_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, pet.getPetName());
            ps.setString(2, pet.getAnimalType());
            ps.setString(3, pet.getBreed());
            ps.setInt(4, pet.getAge());
            ps.setString(5, pet.getGender());
            ps.setInt(6, pet.getWeightKg());
            ps.setInt(7, pet.getUserId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error adding pet: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Pet> getPetsByUser(int userId) {
        List<Pet> petList = new ArrayList<>();
        String sql = "SELECT * FROM tblpets WHERE user_id = ?";

        try (Connection conn = DbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Pet pet = new Pet();
                    pet.setPetId(rs.getInt("pet_id"));
                    pet.setPetName(rs.getString("pet_name"));
                    pet.setAnimalType(rs.getString("animal_type"));
                    pet.setBreed(rs.getString("breed"));
                    pet.setAge(rs.getInt("age"));
                    pet.setGender(rs.getString("gender"));
                    pet.setWeightKg(rs.getInt("weights_kg"));
                    pet.setUserId(rs.getInt("user_id"));
                    petList.add(pet);
                }
            }
        } catch (Exception e) {
            System.out.println("Error retrieving user's pets: " + e.getMessage());
        }
        return petList;
    }

    @Override
    public List<Pet> getAllPets() {
        List<Pet> petList = new ArrayList<>();
        String sql = "SELECT * FROM tblpets";

        try (Connection conn = DbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pet pet = new Pet();

                pet.setPetId(rs.getInt("pet_id"));
                pet.setPetName(rs.getString("pet_name"));
                pet.setAnimalType(rs.getString("animal_type"));
                pet.setBreed(rs.getString("breed"));
                pet.setAge(rs.getInt("age"));
                pet.setGender(rs.getString("gender"));
                pet.setWeightKg(rs.getInt("weights_kg"));
                pet.setUserId(rs.getInt("user_id"));

                petList.add(pet);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving pets: " + e.getMessage());
        }
        return petList;
    }

    @Override
    public Pet getPetById(int petId) {
        String sql = "SELECT * FROM tblpets WHERE pet_id = ?";
        try (Connection conn = DbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, petId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Pet pet = new Pet();
                pet.setPetId(rs.getInt("pet_id"));

                // ---> THIS IS THE FIX: Changed "name" to "pet_name" <---
                pet.setPetName(rs.getString("pet_name"));

                pet.setAnimalType(rs.getString("animal_type"));
                pet.setBreed(rs.getString("breed"));
                pet.setAge(rs.getInt("age"));
                pet.setGender(rs.getString("gender"));
                pet.setWeightKg(rs.getInt("weights_kg"));
                return pet;
            }
        } catch (SQLException e) {
            System.out.println("\tX DAO Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updatePet(Pet pet) {
        // ---> THIS IS THE FIX: Changed "name = ?" to "pet_name = ?" <---
        String sql = "UPDATE tblpets SET pet_name = ?, animal_type = ?, breed = ?, age = ?, gender = ?, weights_kg = ? WHERE pet_id = ?";

        try (Connection conn = DbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pet.getPetName());
            stmt.setString(2, pet.getAnimalType());
            stmt.setString(3, pet.getBreed());
            stmt.setInt(4, pet.getAge());
            stmt.setString(5, pet.getGender());
            stmt.setDouble(6, pet.getWeightKg());
            stmt.setInt(7, pet.getPetId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.out.println("\tX Database Error during update: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletePet(int petId) {
        String sql = "DELETE FROM tblpets WHERE pet_id = ?";

        try (Connection conn = DbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, petId);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0; // Returns true if the pet was successfully removed

        } catch (SQLException e) {
            // NOTE: This will fail if the pet is linked to an existing Appointment (Foreign Key)
            System.out.println("\tX Cannot delete pet: It has existing appointments or medical records.");
            return false;
        }
    }
}