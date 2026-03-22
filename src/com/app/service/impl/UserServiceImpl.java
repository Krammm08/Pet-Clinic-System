package com.app.service.impl;

import com.app.dao.UserDAO;
import com.app.dao.impl.UserDAOImpl;
import com.app.model.User;
import com.app.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public boolean addUser(User user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            System.out.println("Validation Error: Username cannot be blank.");
            return false;
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            System.out.println("Validation Error: Password cannot be blank.");
            return false;
        }
        if (user.getFirstName() == null || user.getFirstName().trim().isEmpty() ||
                user.getLastName() == null || user.getLastName().trim().isEmpty()) {
            System.out.println("Validation Error: First and Last name are required.");
            return false;
        }
        if (user.getAge() < 0) {
            System.out.println("Validation Error: Age cannot be negative.");
            return false;
        }
        if (user.getContactNumber() == null || user.getContactNumber().trim().isEmpty()) {
            System.out.println("Validation Error: Contact number is required.");
            return false;
        }
        if (user.getUserLevel() != 0 && user.getUserLevel() != 1) {
            System.out.println("Validation Error: User level must be 0 (Admin) or 1 (Customer).");
            return false;
        }

        return userDAO.addUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    // ==========================================
    // NEW LOGIN SERVICE
    // ==========================================
    @Override
    public User loginUser(String username, String password) {
        // Validation: Make sure they actually typed something before we ask the database
        if (username == null || username.trim().isEmpty()) {
            System.out.println("Login Error: Username field is empty.");
            return null;
        }

        if (password == null || password.trim().isEmpty()) {
            System.out.println("Login Error: Password field is empty.");
            return null;
        }

        // If validation passes, ask the DAO to check the database
        return userDAO.loginUser(username, password);
    }

    @Override
    public User getUserById(int userId) {
        if (userId <= 0) {
            System.out.println("Validation Error: Invalid User ID.");
            return null;
        }
        return userDAO.getUserById(userId);
    }

    @Override
    public boolean updateUser(User user) {
        if (user.getUserId() <= 0) {
            System.out.println("Validation Error: Cannot update. Invalid User ID.");
            return false;
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            System.out.println("Validation Error: Password cannot be updated to blank.");
            return false;
        }

        return userDAO.updateUser(user);
    }

    @Override
    public boolean deleteUser(int userId) {
        if (userId <= 0) {
            System.out.println("Validation Error: Invalid User ID provided for deletion.");
            return false;
        }
        return userDAO.deleteUser(userId);
    }
}