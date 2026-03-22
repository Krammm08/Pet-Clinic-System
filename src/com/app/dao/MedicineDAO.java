package com.app.dao;
import com.app.model.Medicine;
import java.util.List;

public interface MedicineDAO {
    boolean addMedicine(Medicine medicine);
    List<Medicine> getAllMedicines();
    Medicine getMedicineById(int id);
    boolean updateMedicine(Medicine medicine);
    boolean deleteMedicine(int id);
}
