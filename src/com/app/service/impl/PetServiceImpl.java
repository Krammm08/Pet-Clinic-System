
package com.app.service.impl;

import com.app.dao.PetDAO;
import com.app.dao.impl.PetDAOImpl;
import com.app.model.Pet;
import com.app.service.PetService;

import java.util.ArrayList;
import java.util.List;

public class PetServiceImpl implements PetService {

    private final PetDAO petDAO = new PetDAOImpl();
    
    @Override
    public boolean addPet(Pet pet) {
        if (pet.getPetName() == null || pet.getPetName().trim().isEmpty()){
            System.out.println("Validation Error: Pet name cannot be blank!");
            return false;
        }

        if (pet.getAge() < 0){
            System.out.println("Validation Error: Pet age cannot be lees than 0.");
            return false;
        }

        if (pet.getWeight() <= 0){
            System.out.println("Validation Error: Pet weight must be greater than 0 kg.");
            return false;
        }

        if (pet.getUserId() <= 0){
            System.out.println("Validation Error: Pet must be assigned to a valid owner (User ID).");
            return false;
        }

        return petDAO.addPet(pet);
    }

    @Override
    public List<Pet> getPetsByUser(int userId) {
        if (userId <= 0){
            System.out.println("Validation Error: Invalid User ID provided.");
            return new ArrayList<>();
        }

        return petDAO.getPetsByUser(userId);
    }

    @Override
    public boolean updatePet(Pet pet) {
        if (pet.getPetId() <= 0){
            System.out.println("Validation Error: Cannot update. Invalid Pet ID.");
            return false;
        }

        if (pet.getPetName() == null || pet.getPetName().trim().isEmpty()){
            System.out.println("Validation Error: Pet name cannot be blank!");
            return false;
        }

        if (pet.getAge() < 0){
            System.out.println("Validation Error: Pet age cannot be negative.");
            return false;
        }

        return petDAO.updatePet(pet);
    }

    @Override
    public boolean deletePet(int petId) {
        if (petId <= 0){
            System.out.println("Validation Error: Invalid Pet ID provided for deletion.");
            return false;
        }

        return petDAO.deletePet(petId);
    }
    
}
