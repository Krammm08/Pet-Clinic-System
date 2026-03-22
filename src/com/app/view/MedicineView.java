package com.app.view;

import com.app.exception.DatabaseException;
import com.app.model.Medicine;
import com.app.model.User;
import com.app.dao.MedicineDAO;
import com.app.dao.impl.MedicineDAOImpl;
import com.app.util.Asciiart;
import com.app.util.InputUtil;
import java.util.List;

public class MedicineView {
    
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
                    viewAllVets();
                    break;

                case 2:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    
    private void viewAllVets() {
        try {
            List<Medicine> list = medDAO.getAllMedicines();

            System.out.println("\n===== ALL MEDICINES =====");

            if (list.isEmpty()) {
                System.out.println("No vets found.");
                return;
            }

            for (Medicine med : list) {
                System.out.println("Medicine ID: " + med.getMedicineId());
                System.out.println("Medicince Name: " + med.getMedName());
                System.out.println("Price: " + med.getCost());
                System.out.println("Inventory Count: " + med.getInventoryCount());
                System.out.println("--------------------------");
            }

        } catch (DatabaseException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
