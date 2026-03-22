package com.app.service.impl;

import com.app.model.Medicine;
import com.app.service.MedicineService;
import com.app.dao.MedicineDAO;
import com.app.dao.impl.MedicineDAOImpl;

import java.util.List;

public class MedicineServiceImpl implements MedicineService{

    private MedicineDAO medicineDAO = new MedicineDAOImpl();

    @Override
    public boolean addMedicine(Medicine medicine) {

        if (medicine.getMedName() == null || medicine.getMedName().trim().isEmpty()) {
            System.out.println("Validation Error: Medicine name cannot be blank.");
            return false;
        }

        if (medicine.getCost() < 0) {
            System.out.println("Validation Error: Medicine cost cannot be a negative number.");
            return false;
        }

        if (medicine.getInventoryCount() < 0) {
            System.out.println("Validation Error: Inventory count cannot be negative.");
            return false;
        }


        return medicineDAO.addMedicine(medicine);
    }

    @Override
    public List<Medicine> getAllMedicines() {
        return medicineDAO.getAllMedicines();
    }

    @Override
    public Medicine getMedicineById(int id) {
        // Validation: Ensure the ID is valid before searching the database
        if (id <= 0) {
            System.out.println("Validation Error: Invalid Medicine ID.");
            return null;
        }
        return medicineDAO.getMedicineById(id);
    }

    @Override
    public boolean updateMedicine(Medicine medicine) {

        if (medicine.getMedicineID() <= 0) {
            System.out.println("Validation Error: Cannot update. Invalid Medicine ID.");
            return false;
        }

        // Re-run the basic validations so a medicine isn't accidentally updated to have a blank name or negative stock
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
        // Validation: Prevent accidental deletions
        if (id <= 0) {
            System.out.println("Validation Error: Invalid Medicine ID provided for deletion.");
            return false;
        }
        return medicineDAO.deleteMedicine(id);
    }
}