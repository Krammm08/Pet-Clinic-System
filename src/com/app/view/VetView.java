package com.app.view;

import com.app.exception.DatabaseException;
import com.app.model.User;
import com.app.model.Vet;
import com.app.dao.VetDAO;
import com.app.dao.impl.VetDAOImpl;
import com.app.util.Asciiart;
import com.app.util.InputUtil;
import java.util.List;

public class VetView {
    
    private final VetDAO vetDAO = new VetDAOImpl();
    private final Asciiart art = new Asciiart();
    
    public void show(User user) {

        while (true) {
            
            System.out.println("\n\t================ ALL VETS ================");
            System.out.println("\t|\t1. View All Vets");
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
        List<Vet> list = vetDAO.getAllVets();

        System.out.println("\n\t================ ALL VETS ================");

        if (list.isEmpty()) {
            System.out.println("No vets found.");
            return;
        }

        for (Vet vet : list) {
            System.out.println("Vet ID: " + vet.getVetId());
            System.out.println("Username: " + vet.getVetName());
            System.out.println("Full Name: " + vet.getFirstName() + " " + vet.getLastName());
            System.out.println("Age: " + vet.getAge());
            System.out.println("Gender: " + vet.getGender());
            System.out.println("Contact Number: " + vet.getContactNumber());
            System.out.println("Email Address: " + vet.getEmailAddress());
            System.out.println("Specialization: " + vet.getSpecialization());
            System.out.println("--------------------------");
        }

    }
}
