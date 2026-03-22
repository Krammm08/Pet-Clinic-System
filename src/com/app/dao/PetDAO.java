package com.app.dao;

import com.app.model.Pet;
import java.util.List;

public interface PetDAO {
    boolean addPet(Pet pet);
    List<Pet> getAllPets();
    Pet getPetById(int petId);
    boolean updatePet(Pet pet);
    boolean deletePet(int petId);
}