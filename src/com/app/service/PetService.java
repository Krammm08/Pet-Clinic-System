package com.app.service;

import com.app.model.Pet;
import java.util.List;

public interface PetService {
    
    boolean addPet(Pet pet);
    List<Pet> getPetsByUser(int userId);
    boolean updatePet(Pet pet);
    boolean deletePet(int petId);
}
