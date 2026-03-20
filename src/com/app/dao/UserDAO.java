
package com.app.dao;

import com.app.model.User;
import java.util.List;

public interface UserDAO {
    
     // CREATE
    void addUser(User user);

    // READ (single)
    User getUserById(int id);

    // READ (all)
    List<User> getAllUsers();

    // UPDATE
    void updateUser(User user);

    // DELETE
    void deleteUser(int id);
}
