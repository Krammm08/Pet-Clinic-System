
package com.app.dao.impl;

import com.app.dao.PetDAO;
import com.app.model.Pet;
import com.app.util.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PetDAOImpl implements PetDAO{

    
    // CRUD
    
    //CREATE
    @Override
    public boolean addPet(Pet pet) {
        String query = "INSERT INTO tblpets(pet_name,animal_type,breed,age,gender,weights_kg,user_id) VALUES(?,?,?,?,?,?,?)";

        try (Connection connection = DbConnection.connect();
             PreparedStatement prep = connection.prepareStatement(query);
                ) {

            prep.setString(1, pet.getPetName());
            prep.setString(2, pet.getAnimalType());
            prep.setString(3, pet.getBreed());
            prep.setInt(4, pet.getAge());
            prep.setString(5, pet.getGender());
            prep.setInt(6, pet.getWeight());
            prep.setInt(7, pet.getUserId());

            return prep.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Add Pet Error: " + e.getMessage());
            return false;
        }
    }

    // READ
    @Override
    public List<Pet> getPetsByUser(int userId) {
        List<Pet> list = new ArrayList<>();
        String query = "SELECT * FROM tblpets WHERE user_id=?";

        try (Connection conn = DbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query);
                ) {

            prep.setInt(1, userId);
            ResultSet result = prep.executeQuery();

            while (result.next()) {
                Pet pet = new Pet();
                pet.setPetId(result.getInt("pet_id"));
                pet.setPetName(result.getString("pet_name"));
                pet.setAnimalType(result.getString("animal_type"));
                pet.setBreed(result.getString("breed"));
                pet.setAge(result.getInt("age"));
                pet.setGender(result.getString("gender"));
                pet.setWeight(result.getInt("weights_kg"));
                pet.setUserId(result.getInt("user_id"));

                list.add(pet);
            }

        } catch (Exception e) {
            System.out.println("Fetch Pets Error: " + e.getMessage());
        }

        return list;
    }

    // UPDATE
    @Override
    public boolean updatePet(Pet pet) {
        String query = "UPDATE tblpets SET pet_name=?,animal_type=?,breed=?,age=?,gender=?,weights_kg=? WHERE pet_id=?";

        try (Connection conn = DbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query);
                ) {

            prep.setString(1, pet.getPetName());
            prep.setString(2, pet.getAnimalType());
            prep.setString(3, pet.getBreed());
            prep.setInt(4, pet.getAge());
            prep.setString(5, pet.getGender());
            prep.setInt(6, pet.getWeight());
            prep.setInt(7, pet.getPetId());

            return prep.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Update Pet Error: " + e.getMessage());
            return false;
        }
    }

    // DELETE
    @Override
    public boolean deletePet(int petId) {
        String sql = "DELETE FROM tblpets WHERE pet_id=?";

        try (Connection conn = DbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(sql);
                ) {

            prep.setInt(1, petId);
            return prep.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Delete Pet Error: " + e.getMessage());
            return false;
        }
    }
    
}
