package com.app.service.impl;

import com.app.dao.MedicineDAO;
import com.app.dao.impl.MedicineDAOImpl;
import com.app.model.Medicine;
import com.app.service.MedicineService;

import java.util.List;

public class MedicineServiceImpl implements MedicineService {

    // The Service owns a copy of the DAO to talk to the database
    private MedicineDAO medicineDAO = new MedicineDAOImpl();

    @Override
    public boolean addMedicine(Medicine medicine) {
        // BUSINESS LOGIC & VALIDATION

        // Rule 1: Medicine must have a name
        if (medicine.getMedName() == null || medicine.getMedName().trim().isEmpty()) {
            System.out.println("Validation Error: Medicine name cannot be blank.");
            return false;
        }

        // Rule 2: Cost cannot be negative
        if (medicine.getCost() < 0) {
            System.out.println("Validation Error: Medicine cost cannot be a negative number.");
            return false;
        }

        // Rule 3: Inventory count cannot be negative
        if (medicine.getInventoryCount() < 0) {
            System.out.println("Validation Error: Inventory count cannot be negative.");
            return false;
        }

        // If all rules pass, execute the DAO
        return medicineDAO.addMedicine(medicine);
    }

    @Override
    public List<Medicine> getAllMedicines() {
        return medicineDAO.getAllMedicines();
    }

    @Override
    public Medicine getMedicineById(int id) {
        if (id <= 0) {
            System.out.println("Validation Error: Invalid Medicine ID.");
            return null;
        }
        return medicineDAO.getMedicineById(id);
    }

    @Override
    public boolean updateMedicine(Medicine medicine) {
        if (medicine.getMedicineId() <= 0) {
            System.out.println("Validation Error: Cannot update. Invalid Medicine ID.");
            return false;
        }

        if (medicine.getMedName() == null || medicine.getMedName().trim().isEmpty()) {
            System.out.println("Validation Error: Medicine name cannot be updated to blank.");
            return false;
        }

        if (medicine.getCost() < 0 || medicine.getInventoryCount() < 0) {
            System.out.println("Validation Error: Cost and Inventory count cannot be negative.");
            return false;
        }

        return medicineDAO.updateMedicine(medicine);
    }

    @Override
    public boolean deleteMedicine(int id) {
        if (id <= 0) {
            System.out.println("Validation Error: Invalid Medicine ID provided for deletion.");
            return false;
        }
        return medicineDAO.deleteMedicine(id);
    }
}