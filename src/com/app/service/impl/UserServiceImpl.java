
package com.app.service.impl;

import com.app.dao.UserDAO;
import com.app.dao.impl.UserDAOImpl;
import com.app.model.User;
import com.app.service.UserService;
// import com.app.util.PasswordUtil;

public class UserServiceImpl implements UserService {

    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    public User login(String username, String password) {
        //String hashed = PasswordUtil.hashPassword(password);
        //return userDAO.login(username, hashed);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean register(User user) {
        //user.setPassword(PasswordUtil.hashPassword(user.getPassword()));
        //return userDAO.register(user);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

