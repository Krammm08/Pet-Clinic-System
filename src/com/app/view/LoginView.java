package com.app.view;

import com.app.model.User;
import com.app.service.UserService;
import com.app.service.impl.UserServiceImpl;
import com.app.util.InputUtil;

public class LoginView {

    private final UserService userService = new UserServiceImpl();

    // This is the new "Front Door" of your app
    public User showWelcomeMenu() {
        while (true) {
            System.out.println("\n\t=== WELCOME TO PET CLINIC ===");
            System.out.println("\t|\t1. Login");
            System.out.println("\t|\t2. Register New Account");
            System.out.println("\t|\t3. Exit App");
            System.out.println("\t=============================");

            int choice = InputUtil.getInt("\tChoose an option: ");

            switch (choice) {
                case 1:
                    User user = login();
                    // If login is successful, return the user to Main to open the dashboard
                    if (user != null) {
                        return user;
                    }
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.out.println("\tExiting... Have a great day!");
                    System.exit(0);
                default:
                    System.out.println("\tX Invalid choice. Please try again.");
            }
        }
    }

    User login() {
        System.out.println("\n\t--- LOGIN ---");
        String user = InputUtil.getString("\tUsername: ");
        String pass = InputUtil.getString("\tPassword: ");

        // Ensure this method matches what you named it in UserService
        User loggedInUser = userService.loginUser(user, pass);

        if (loggedInUser != null) {
            System.out.println("\t-> Login successful!");
        } else {
            System.out.println("\tX Invalid username or password.");
        }
        return loggedInUser;
    }

    private void register() {
        System.out.println("\n\t--- REGISTER NEW ACCOUNT ---");

        // Ask for EVERY field required by your database
        String firstName = InputUtil.getString("\tFirst Name: ");
        String lastName = InputUtil.getString("\tLast Name: ");
        int age = InputUtil.getInt("\tAge: "); // Notice we use getInt here!
        String gender = InputUtil.getString("\tGender (Male/Female): ");
        String contact = InputUtil.getString("\tContact Number: ");
        String email = InputUtil.getString("\tEmail Address: ");
        String city = InputUtil.getString("\tCity Address: ");
        String user = InputUtil.getString("\tCreate Username: ");
        String pass = InputUtil.getString("\tCreate Password: ");

        // Attach it all to the User object
        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setAge(age);
        newUser.setGender(gender);
        newUser.setContactNumber(contact);

        // Note: Check your User.java to make sure these match your exact setter names!
        // They might be named setEmail() or setCity() depending on how you typed them.
        newUser.setEmailAddress(email);
        newUser.setCityAddress(city);

        newUser.setUsername(user);
        newUser.setPassword(pass);

        // Set to Customer level (1) by default
        newUser.setUserLevel(1);

        // Send to the database
        boolean success = userService.addUser(newUser);

        if (success) {
            System.out.println("\t-> Registration successful! You can now log in.");
        } else {
            System.out.println("\tX Registration failed. Please try a different username.");
        }
    }
}