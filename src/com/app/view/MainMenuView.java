package com.app.view;

import com.app.exception.DatabaseException;
import com.app.model.User;

public class MainMenuView {

    private final PetView petView = new PetView();
    private final AppointmentView appointmentView = new AppointmentView();
    private final TransactionView transactionView = new TransactionView();
    private final MedicineView medicineView = new MedicineView();
    private final ProcedureView procedureView = new ProcedureView();

    public void showMenu(User user) throws DatabaseException {
        if (user.isAdmin()) {
            showAdminMenu(user);
        } else {
            showCustomerMenu(user);
        }
    }

    private void showCustomerMenu(User user) throws DatabaseException {
        while (true) {
            System.out.println("\n\t======= CUSTOMER DASHBOARD =======");
            System.out.println("\t|\t1. Manage My Pets");
            System.out.println("\t|\t2. Book/View Appointments");
            System.out.println("\t|\t3. Billing & Transactions");
            System.out.println("\t|\t4. Logout");
            System.out.println("\t====================================");

            int choice = com.app.util.InputUtil.getInt("\tSelect: ");

            switch (choice) {
                case 1: petView.show(user); break;
                case 2: appointmentView.customerMenu(user); break;
                case 3: transactionView.customerMenu(user); break;
                case 4: return; // Back to login
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private void showAdminMenu(User user) throws DatabaseException {
        while (true) {
            System.out.println("\n\t========= ADMIN DASHBOARD =========");
            System.out.println("\t|\t1. Manage Appointments (Approve/Decline)");
            System.out.println("\t|\t2. Manage Procedures & Diagnosis");
            System.out.println("\t|\t3. View All Transactions");
            System.out.println("\t|\t4. Inventory (Medicines)");
            System.out.println("\t|\t5. Logout");
            System.out.println("\t====================================");

            int choice = com.app.util.InputUtil.getInt("\tSelect: ");

            switch (choice) {
                case 1: appointmentView.adminMenu(); break;
                case 2: procedureView.show(); break;
                case 3: transactionView.adminMenu(); break;
                case 4: medicineView.show(user); break;
                case 5: return;
                default: System.out.println("Invalid choice.");
            }
        }
    }
}