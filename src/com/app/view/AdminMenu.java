package com.app.view;

import com.app.exception.DatabaseException;
import com.app.model.User;
import com.app.util.Asciiart;
import com.app.util.InputUtil;

public class AdminMenu {
    
    private final Asciiart art = new Asciiart();

    public void show(User user) throws DatabaseException {
        while (true) {
            String dog = art.longDog();
            System.out.println(dog);
            System.out.println("\n\t=============== ADMIN MENU ===============");
            System.out.println("Welcome Admin: " + user.getFirstName());
            System.out.println("\t|\t1. Manage Users");
            System.out.println("\t|\t2. Manage Offered Services");
            System.out.println("\t|\t3. Manage Medicines");
            System.out.println("\t|\t4. Manage Vets");
            System.out.println("\t|\t5. Approve Appointments");
            System.out.println("\t|\t6. Manage Transactions");
            System.out.println("\t|\t7. Procedure Management");
            System.out.println("\t|\t8. Logout");


            System.out.println("\t==========================================");
            int choice = InputUtil.getInt("\tChoose option: ");
            System.out.println("\t==========================================\n");
            switch (choice) {

                case 1:
                    UserView userView = new UserView();
                    userView.show(user);
                    break;

                case 2:
                    OfferedServiceView serviceView = new OfferedServiceView();
                    serviceView.show(user);
                    break;

                case 3:
                    MedicineView medView = new MedicineView();
                    medView.show(user);
                    break;

                case 4:
                    VetView vetView = new VetView();
                    vetView.show(user);
                    break;

                case 5:
                    AppointmentView appointmentView = new AppointmentView();
                    appointmentView.adminMenu();
                    break;

                case 6:
                    TransactionView transactionView = new TransactionView();
                    transactionView.adminMenu();
                    break;

                case 7:
                    ProcedureView procedureView = new ProcedureView();
                    procedureView.show();
                    break;

                case 8:
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
