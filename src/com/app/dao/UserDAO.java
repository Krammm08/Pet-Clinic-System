package com.app.dao;

import java.util.List;
import com.app.model.User;
import com.app.exception.DatabaseException;

public interface UserDAO {

    // Create Account (Register)
    boolean insertUser(User user) throws DatabaseException;

    // Login
    User getUserByUsername(String username) throws DatabaseException;

    // Get All Users (Admin use)
    List<User> getAllUsers() throws DatabaseException;

    // Get User by ID
    User getUserById(int userId) throws DatabaseException;

    // Update User
    boolean updateUser(User user) throws DatabaseException;

    // Delete User
    boolean deleteUser(int userId) throws DatabaseException;
}
