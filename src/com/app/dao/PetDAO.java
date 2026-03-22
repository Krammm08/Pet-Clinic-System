package com.app.dao;

import java.util.List;
import com.app.model.Pet;
import com.app.exception.DatabaseException;

public interface PetDAO {

    // Add new pet
    boolean insertPet(Pet pet) throws DatabaseException;

    // Get all pets by user
    List<Pet> getPetsByUserId(int userId) throws DatabaseException;

    // Get pet by ID
    Pet getPetById(int petId) throws DatabaseException;

    // Update pet
    boolean updatePet(Pet pet) throws DatabaseException;

    // Delete pet
    boolean deletePet(int petId) throws DatabaseException;
}
