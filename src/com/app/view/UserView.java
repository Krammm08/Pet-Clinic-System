package com.app.view;

import com.app.exception.DatabaseException;
import com.app.model.User;
import com.app.dao.UserDAO;
import com.app.dao.impl.UserDAOImpl;
import com.app.util.Asciiart;
import com.app.util.InputUtil;
import java.util.List;

public class UserView {
    
    private final UserDAO userDAO = new UserDAOImpl();
    private final Asciiart art = new Asciiart();
    
    public void show(User user) {

        while (true) {
            
            System.out.println("\n\t================ ALL USERS ===============");
            System.out.println("\t|\t1. View All Users");
            System.out.println("\t|\t2. Back");
            System.out.println("\t==========================================");
            int choice = InputUtil.getInt("\tChoose option: ");
            System.out.println("\t==========================================\n");
            switch (choice) {

                case 1:
                    viewAllUsers();
                    break;

                case 2:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    
    private void viewAllUsers() {
        try {
            List<User> list = userDAO.getAllUsers();

            System.out.println("\n\t================ ALL USERS ===============");

            if (list.isEmpty()) {
                System.out.println("No users found.");
                return;
            }

            for (User user : list) {
                System.out.println("User ID: " + user.getUserId());
                System.out.println("Username: " + user.getUsername());
                System.out.println("Full Name: " + user.getFirstName() + " " + user.getLastName());
                System.out.println("Age: " + user.getAge());
                System.out.println("Gender: " + user.getGender());
                System.out.println("Contact Number: " + user.getContactNumber());
                System.out.println("Email Address: " + user.getEmailAddress());
                System.out.println("City Address: " + user.getCityAddress());
                System.out.println("--------------------------");
            }

        } catch (DatabaseException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
