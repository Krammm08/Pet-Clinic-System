package com.app.service.impl;

import com.app.dao.UserDAO;
import com.app.dao.impl.UserDAOImpl;
import com.app.exception.AuthenticationException;
import com.app.exception.DatabaseException;
import com.app.exception.ValidationException; // Add this import
import com.app.model.User;
import com.app.service.AuthService;

public class AuthServiceImpl implements AuthService {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public User login(String username, String password) throws AuthenticationException, DatabaseException {
        // ... (Keep your existing login code here exactly as it was) ...
        try {
            User user = userDAO.loginUser(username, password);
            if (user == null) {
                throw new AuthenticationException("Invalid username or password.");
            }
            return user;
        } catch (AuthenticationException e) {
            throw e;
        } catch (Exception e) {
            throw new DatabaseException("Could not connect to the database.", e);
        }
    }
    @Override
    public boolean register(User user) throws ValidationException, DatabaseException {
        // 1. Validate the critical inputs
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new ValidationException("Username cannot be blank.");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new ValidationException("Password cannot be blank.");
        }
        if (user.getAge() < 0) {
            throw new ValidationException("Age cannot be a negative number.");
        }

        // 2. Force all new public registrations to be Customers (Level 1)
        // You don't want someone secretly registering as an Admin!
        user.setUserLevel(1);

        try {
            // 3. Ask the DAO to save them to MySQL
            boolean isAdded = userDAO.addUser(user);

            if (!isAdded) {
                // If the DAO fails (often because a username already exists in the DB)
                throw new DatabaseException("Registration failed. Username might already be taken.");
            }

            return true;

        } catch (Exception e) {
            throw new DatabaseException("Database connection error during registration.", e);
        }
    }
}