package com.app.dao.impl;

import com.app.dao.VetDAO;
import com.app.model.Vet;
import com.app.util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VetDAOImpl implements VetDAO{
    @Override
    public boolean addVet(Vet vet){
        String query = "INSERT INTO tblvets (vet_name, last_name, first_name, age, gender, contact_number, email_address, specialization) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DbConnection.connect();
             PreparedStatement prep = connection.prepareStatement(query)){
            prep.setString(1, vet.getVetName());
            prep.setString(2, vet.getLastName());
            prep.setString(3, vet.getFirstName());
            prep.setInt(4, vet.getAge());
            prep.setString(5, vet.getGender());
            prep.setString(6, vet.getContactNumber());
            prep.setString(7, vet.getEmail());
            prep.setString(8, vet.getSpecialization());

            return prep.executeUpdate() > 0;
        } catch (Exception e){
            System.out.println("Error adding vet: " + e.getMessage());
        }
        return false;
    }
    @Override
    public List<Vet> getAllVets(){
        List<Vet> vetList = new ArrayList<>();
        String query = "SELECT * FROM tblvets";

        try(Connection connection = DbConnection.connect();
            PreparedStatement prep = connection.prepareStatement(query);
            ResultSet result = prep.executeQuery()){

            while (result.next()){
                Vet vet = new Vet();

                vet.setVetID(result.getInt("vet_id"));
                vet.setVetName(result.getString("vet_name"));
                vet.setLastName(result.getString("last_name"));
                vet.setFirstName(result.getString("first_name"));
                vet.setAge(result.getInt("age"));
                vet.setGender(result.getString("gender"));
                vet.setContactNumber(result.getString("contact_number"));
                vet.setEmail(result.getString("email_address"));
                vet.setSpecialization(result.getString("specialization"));

                vetList.add(vet);
            }
        }catch (Exception e){
            System.out.println("Error retrieving vets: " + e.getMessage());
        }
        return vetList;
    }
    @Override
    public Vet getVetById(int id){
        return null;
    }
    @Override
    public boolean updateVet(Vet vet){
        return false;
    }
    @Override
    public boolean deleteVet(int id){
        return false;
    }
}
