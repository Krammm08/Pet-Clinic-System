package com.app.dao.impl;

import com.app.dao.MedicineDAO;
import com.app.model.Medicine;
import com.app.util.DbConnection;
import com.app.util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MedicineDAOImpl implements MedicineDAO {

    @Override
    public boolean addMedicine(Medicine medicine) {
        // Skipping medicine_id because MySQL auto-increments it
        String sql = "INSERT INTO tblmedicines (med_name, cost, inventory_count) VALUES (?, ?, ?)";

        try (Connection conn = DbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, medicine.getMedName());
            ps.setInt(2, medicine.getCost());
            ps.setInt(3, medicine.getInventoryCount());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error adding medicine: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Medicine> getAllMedicines() {
        List<Medicine> medicineList = new ArrayList<>();
        String sql = "SELECT * FROM tblmedicines";

        try (Connection conn = DbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Medicine medicine = new Medicine();

                medicine.setMedicineId(rs.getInt("medicine_id"));
                medicine.setMedName(rs.getString("med_name"));
                medicine.setCost(rs.getInt("cost"));
                medicine.setInventoryCount(rs.getInt("inventory_count"));

                medicineList.add(medicine);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving medicines: " + e.getMessage());
        }
        return medicineList;
    }

    @Override
    public Medicine getMedicineById(int id) {
        return null; // TODO: Implement later
    }

    @Override
    public boolean updateMedicine(Medicine medicine) {
        return false; // TODO: Implement later
    }

    @Override
    public boolean deleteMedicine(int id) {
        return false; // TODO: Implement later
    }
}