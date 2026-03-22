package com.app.service.impl;

import com.app.dao.VetDAO;
import com.app.dao.impl.VetDAOImpl;
import com.app.model.Vet;
import com.app.service.VetService;

import java.util.List;

public class VetServiceImpl implements VetService {

    private VetDAO vetDAO = new VetDAOImpl();

    @Override
    public boolean addVet(Vet vet) {
        // Validation 1: Names cannot be empty
        if (vet.getFirstName() == null || vet.getFirstName().trim().isEmpty() ||
                vet.getLastName() == null || vet.getLastName().trim().isEmpty()) {
            System.out.println("Validation Error: Vet's first and last name are required.");
            return false;
        }

        // Validation 2: Age cannot be negative
        if (vet.getAge() < 0) {
            System.out.println("Validation Error: Age cannot be negative.");
            return false;
        }

        // Validation 3: Contact info is important for a clinic
        if (vet.getContactNumber() == null || vet.getContactNumber().trim().isEmpty()) {
            System.out.println("Validation Error: Contact number is required.");
            return false;
        }

        // Validation 4: Specialization check (e.g., "Surgeon", "General Practice")
        if (vet.getSpecialization() == null || vet.getSpecialization().trim().isEmpty()) {
            System.out.println("Validation Error: Specialization cannot be blank.");
            return false;
        }

        return vetDAO.addVet(vet);
    }

    @Override
    public List<Vet> getAllVets() {
        return vetDAO.getAllVets();
    }

    @Override
    public Vet getVetById(int vetId) {
        if (vetId <= 0) {
            System.out.println("Validation Error: Invalid Vet ID.");
            return null;
        }
        return vetDAO.getVetById(vetId);
    }

    @Override
    public boolean updateVet(Vet vet) {
        if (vet.getVetId() <= 0) {
            System.out.println("Validation Error: Cannot update. Invalid Vet ID.");
            return false;
        }

        if (vet.getFirstName() == null || vet.getFirstName().trim().isEmpty() ||
                vet.getLastName() == null || vet.getLastName().trim().isEmpty()) {
            System.out.println("Validation Error: Vet's name cannot be updated to blank.");
            return false;
        }

        return vetDAO.updateVet(vet);
    }

    @Override
    public boolean deleteVet(int vetId) {
        if (vetId <= 0) {
            System.out.println("Validation Error: Invalid Vet ID provided for deletion.");
            return false;
        }
        return vetDAO.deleteVet(vetId);
    }
}