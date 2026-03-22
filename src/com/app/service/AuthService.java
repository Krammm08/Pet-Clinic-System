package com.app.service;

import com.app.exception.AuthenticationException;
import com.app.exception.DatabaseException;
import com.app.exception.ValidationException; // Add this import
import com.app.model.User;

public interface AuthService {
    User login(String username, String password) throws AuthenticationException, DatabaseException;

    // NEW: The register blueprint
    boolean register(User user) throws ValidationException, DatabaseException;
}