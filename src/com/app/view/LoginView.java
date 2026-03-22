package com.app.view;

import com.app.exception.AuthenticationException;
import com.app.exception.DatabaseException;
import com.app.model.User;
import com.app.service.AuthService;
import com.app.service.impl.AuthServiceImpl;
import com.app.util.Asciiart;
import com.app.util.InputUtil;

public class LoginView {

    private final AuthService authService = new AuthServiceImpl();
    private final Asciiart art = new Asciiart();

    public User login() {
        String bunny = art.bunny();
        System.out.println(bunny);
        System.out.println("\n\t================= LOGIN ==================");

        String username = InputUtil.getNonEmptyString("\tUsername: ");
        String password = InputUtil.getNonEmptyString("\tPassword: ");
        System.out.println("\t==========================================\n");
        try {
            User user = authService.login(username, password);

            System.out.println("Login successful!");
            System.out.println("Welcome, " + user.getFirstName() + "!");

            return user;

        } catch (AuthenticationException e) {
            System.out.println("X " + e.getMessage());
        } catch (DatabaseException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        return null;
    }
}
