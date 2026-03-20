
package com.app.service;

import com.app.model.User;

public interface UserService {

    User login(String username, String password);

    boolean register(User user);
}
