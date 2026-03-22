package com.app.dao.impl;

import com.app.dao.PetDAO;
import com.app.model.Pet;
import com.app.util.DbConnection; // Using your connection utility

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PetDAOImpl implements PetDAO {

    @Override
    public boolean addPet(Pet pet) {
        // Skipping pet_id because MySQL auto-increments it
        String sql = "INSERT INTO tblpets (pet_name, animal_type, breed, age, gender, weight_kg, user_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

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
                pet.setWeightKg(rs.getInt("weight_kg"));
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
        return null; // TODO: Implement later
    }

    @Override
    public boolean updatePet(Pet pet) {
        return false; // TODO: Implement later
    }

    @Override
    public boolean deletePet(int petId) {
        return false; // TODO: Implement later
    }
}