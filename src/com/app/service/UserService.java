package com.app.service;

import com.app.model.User;
import java.util.List;

public interface UserService {
    boolean addUser(User user);
    List<User> getAllUsers();
    User getUserById(int userId);
    boolean updateUser(User user);
    boolean deleteUser(int userId);

    // NEW: Service login method
    User loginUser(String username, String password);
}