package com.app.main;

import com.app.model.User;
import com.app.view.LoginView;

public class Main {
    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        User loggedInUser = null;

        // Keep asking them to log in until they get it right
        while (loggedInUser == null) {
            loggedInUser = loginView.login();
        }

        System.out.println("\n--- MAIN MENU ---");
        if (loggedInUser.isAdmin()) {
            System.out.println("Loading Admin Dashboard...");
        } else if (loggedInUser.isCustomer()) {
            System.out.println("Loading Customer Portal...");
        }
    }
}