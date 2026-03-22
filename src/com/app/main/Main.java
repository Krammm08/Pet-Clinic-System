package com.app.main;

import com.app.exception.DatabaseException;
import com.app.model.User;
import com.app.view.LoginView;
import com.app.view.MainMenuView;

public class Main {
    public static void main(String[] args) throws DatabaseException {
        LoginView loginView = new LoginView();
        MainMenuView mainMenuView = new MainMenuView();

        // This outer loop ensures that if a user logs out,
        // they are sent back to the Welcome Menu instead of the app crashing.
        while (true) {

            // 1. Show the Welcome Menu (Login/Register/Exit)
            User loggedInUser = loginView.showWelcomeMenu();

            // 2. Once logged in successfully, open their specific dashboard
            if (loggedInUser != null) {
                mainMenuView.showMenu(loggedInUser);
            }
        }
    }
}