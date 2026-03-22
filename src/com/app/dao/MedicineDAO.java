package com.app.dao;
import com.app.exception.DatabaseException;
import com.app.model.Medicine;
import java.util.List;

public interface MedicineDAO {
    public boolean addMedicine(Medicine medicine) throws DatabaseException;
    public List<Medicine> getAllMedicines() throws DatabaseException;
    Medicine getMedicineById(int id);
    boolean updateMedicine(Medicine medicine);
    public boolean updateStock(int medId, int newCount) throws DatabaseException;
    boolean deleteMedicine(int id);
}
