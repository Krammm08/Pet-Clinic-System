package com.app.service;

import com.app.model.Medicine;
import java.util.List;

public interface MedicineService {
    boolean addmedicine(Medicine medicine);
    List<Medicine> getAllMedicines();
    Medicine getMedicineById(int id);
    boolean updateMedicine(Medicine medicine);
    boolean deleteMedicine(int id);
}
