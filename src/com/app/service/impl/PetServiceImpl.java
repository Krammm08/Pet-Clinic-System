
package com.app.service.impl;

import com.app.dao.PetDAO;
import com.app.dao.impl.PetDAOImpl;
import com.app.model.Pet;
import com.app.service.PetService;
import java.util.List;

public class PetServiceImpl implements PetService {

    private final PetDAO petDAO = new PetDAOImpl();
    
    @Override
    public boolean addPet(Pet pet) {
        return petDAO.addPet(pet);
    }

    @Override
    public List<Pet> getPetsByUser(int userId) {
        return petDAO.getPetsByUser(userId);
    }

    @Override
    public boolean updatePet(Pet pet) {
        return petDAO.updatePet(pet);
    }

    @Override
    public boolean deletePet(int petId) {
        return petDAO.deletePet(petId);
    }
    
}
