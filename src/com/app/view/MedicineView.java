package com.app.view;

import com.app.model.Medicine;
import com.app.model.User;
import com.app.dao.MedicineDAO;
import com.app.dao.impl.MedicineDAOImpl;
import com.app.util.Asciiart;
import com.app.util.InputUtil;
import java.util.List;

public class MedicineView {

    // Note: It's usually better practice to use MedicineService here instead of the DAO directly,
    // but the DAO will work perfectly for viewing data!
    private final MedicineDAO medDAO = new MedicineDAOImpl();
    private final Asciiart art = new Asciiart();

    public void show(User user) {

        while (true) {

            System.out.println("\n\t============== ALL MEDICINES =============");
            System.out.println("\t|\t1. View All Medicine");
            System.out.println("\t|\t2. Back");
            System.out.println("\t==========================================");
            int choice = InputUtil.getInt("\tChoose option: ");
            System.out.println("\t==========================================\n");
            switch (choice) {

                case 1:
                    viewAllMedicines(); // Renamed to make sense!
                    break;

                case 2:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void viewAllMedicines() { // Renamed from viewAllVets

        // No try-catch needed here because the DAO handles exceptions internally!
        List<Medicine> list = medDAO.getAllMedicines();
        if (list.isEmpty()) {
            System.out.println("No medicines found in the inventory."); // Fixed text
            return;
        }

        System.out.println("\n\t===== ALL MEDICINES =====");
        for (Medicine m : list) {
            System.out.println("\tMedicine ID: " + m.getMedicineId());
            System.out.println("\tMedicine Name: " + m.getMedName());
            System.out.println("\tPrice: ₱" + m.getCost());
            System.out.println("\tInventory Count: " + m.getInventoryCount());
            System.out.println("\t--------------------------");
        }


    }
}