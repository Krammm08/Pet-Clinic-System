package com.app.service.impl;

import com.app.dao.PetDAO;
import com.app.dao.impl.PetDAOImpl;
import com.app.model.Pet;
import com.app.service.PetService;

import java.util.List;

public class PetServiceImpl implements PetService {

    private PetDAO petDAO = new PetDAOImpl();

    @Override
    public boolean addPet(Pet pet) {
        // Validation 1: Pet must belong to a valid owner (User ID)
        if (pet.getUserId() <= 0) {
            System.out.println("Validation Error: Pet must be linked to a valid User ID.");
            return false;
        }

        // Validation 2: Pet must have a name
        if (pet.getPetName() == null || pet.getPetName().trim().isEmpty()) {
            System.out.println("Validation Error: Pet name cannot be blank.");
            return false;
        }

        // Validation 3: Must define what kind of animal it is (Dog, Cat, Bird, etc.)
        if (pet.getAnimalType() == null || pet.getAnimalType().trim().isEmpty()) {
            System.out.println("Validation Error: Animal type cannot be blank.");
            return false;
        }

        // Validation 4: Age and weight cannot be negative numbers
        if (pet.getAge() < 0) {
            System.out.println("Validation Error: Pet age cannot be negative.");
            return false;
        }
        if (pet.getWeightKg() < 0) {
            System.out.println("Validation Error: Pet weight cannot be negative.");
            return false;
        }

        // If all rules pass, execute the DAO
        return petDAO.addPet(pet);
    }

    @Override
    public List<Pet> getAllPets() {
        return petDAO.getAllPets();
    }

    @Override
    public Pet getPetById(int petId) {
        if (petId <= 0) {
            System.out.println("Validation Error: Invalid Pet ID.");
            return null;
        }
        return petDAO.getPetById(petId);
    }

    @Override
    public boolean updatePet(Pet pet) {
        if (pet.getPetId() <= 0) {
            System.out.println("Validation Error: Cannot update. Invalid Pet ID.");
            return false;
        }

        if (pet.getPetName() == null || pet.getPetName().trim().isEmpty()) {
            System.out.println("Validation Error: Pet name cannot be updated to blank.");
            return false;
        }

        if (pet.getAge() < 0 || pet.getWeightKg() < 0) {
            System.out.println("Validation Error: Age and weight cannot be updated to negative numbers.");
            return false;
        }

        return petDAO.updatePet(pet);
    }

    @Override
    public boolean deletePet(int petId) {
        if (petId <= 0) {
            System.out.println("Validation Error: Invalid Pet ID provided for deletion.");
            return false;
        }
        return petDAO.deletePet(petId);
    }
}