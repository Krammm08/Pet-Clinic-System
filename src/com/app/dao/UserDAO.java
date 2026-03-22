package com.app.dao;

import com.app.model.User;
import java.util.List;

public interface UserDAO {
    boolean addUser(User user);
    List<User> getAllUsers();
    User getUserById(int userId);
    boolean updateUser(User user);
    boolean deleteUser(int userId);

    User loginUser(String username, String password);
}