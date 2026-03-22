package com.app.view;

import com.app.model.User;
import com.app.util.Asciiart;
import com.app.util.InputUtil;

public class CustomerMenu {
    private final Asciiart art = new Asciiart();

    public void show(User user) {

        while (true) {
            
            System.out.println("\n\t============== CUSTOMER MENU =============");
            System.out.println("\t|\tWelcome: " + user.getFirstName());
            System.out.println("\t|\t1. Manage Pets");
            System.out.println("\t|\t2. View Appointment");
            System.out.println("\t|\t3. View Transactions");
            System.out.println("\t|\t4. Logout");
            System.out.println("\t==========================================");
            int choice = InputUtil.getInt("\tChoose option: ");
            System.out.println("\t==========================================\n");
            switch (choice) {

                case 1:
                    PetView petView = new PetView();
                    petView.show(user);
                    break;


                case 2:
                    AppointmentView appointmentView = new AppointmentView();
                    appointmentView.customerMenu(user);
                    break;

                case 3:
                    TransactionView transactionView = new TransactionView();
                    transactionView.customerMenu(user);
                    break;

                case 4:
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
