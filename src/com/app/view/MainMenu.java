package com.app.view;

import com.app.model.User;
import com.app.util.Asciiart;
import com.app.util.InputUtil;

public class MainMenu {

    private final LoginView loginView = new LoginView();
    private final RegisterView registerView = new RegisterView();
    private final Asciiart art = new Asciiart();

    public void show() {

        while (true) {
            String dog = art.longDog();
            System.out.println(dog);
            System.out.println("\n\t=========== PET CLINIC SYSTEM ============");
            System.out.println("\t|\t1. Login\t\t\t|");
            System.out.println("\t|\t2. Register\t\t\t|");
            System.out.println("\t|\t3. Exit\t\t\t\t|");

            System.out.println("\t==========================================");
            int choice = InputUtil.getInt("\tChoose option: ");
            System.out.println("\t==========================================\n");
            String cat = art.cat();
            System.out.println(cat);
            switch (choice) {
                case 1:
                    User user = loginView.login();

                    if (user != null) {
                        if (user.isAdmin()) {
                            System.out.println("Admin Access Granted");
                            AdminMenu adminMenu = new AdminMenu();
                            adminMenu.show(user);
                        } else {
                            System.out.println("Customer Access Granted");
                            CustomerMenu customerMenu = new CustomerMenu();
                            customerMenu.show(user);
                        }

                    }
                    break;

                case 2:
                    registerView.register();
                    break;

                case 3:
                    System.out.println("Thank you for using the system!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
