package com.app.view;

import com.app.exception.DatabaseException;
import com.app.exception.ValidationException;
import com.app.model.User;
import com.app.service.AuthService;
import com.app.service.impl.AuthServiceImpl;
import com.app.util.Asciiart;
import com.app.util.InputUtil;

public class RegisterView {

    private final AuthService authService = new AuthServiceImpl();
    private final Asciiart art = new Asciiart();

    public void register() {
        String bunny = art.bunny();
        System.out.println(bunny);
        System.out.println("\n\t================ REGISTER ================");

        User user = new User();

        user.setUsername(InputUtil.getNonEmptyString("\tUsername: "));
        user.setPassword(InputUtil.getNonEmptyString("\tPassword: "));
        user.setFirstName(InputUtil.getNonEmptyString("\tFirst Name: "));
        user.setLastName(InputUtil.getNonEmptyString("\tLast Name: "));
        user.setAge(InputUtil.getInt("\tAge: "));
        user.setGender(InputUtil.getNonEmptyString("\tGender: "));
        user.setContactNumber(InputUtil.getNonEmptyString("\tContact Number: "));
        user.setEmailAddress(InputUtil.getNonEmptyString("\tEmail Address: "));
        user.setCityAddress(InputUtil.getNonEmptyString("\tCity Address: "));
        System.out.println("\t==========================================\n");
        try {
            boolean success = authService.register(user);

            if (success) {
                System.out.println("Registration successful! You can now login.");
            } else {
                System.out.println("Registration failed.");
            }

        } catch (ValidationException e) {
            System.out.println("Validation Error: " + e.getMessage());
        } catch (DatabaseException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }
}
