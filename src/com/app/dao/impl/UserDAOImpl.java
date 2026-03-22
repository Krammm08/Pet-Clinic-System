package com.app.dao.impl;

import com.app.dao.UserDAO;
import com.app.model.User;
import com.app.util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean addUser(User user) {
        String sql = "INSERT INTO tblusers (username, password, last_name, first_name, age, gender, contact_number, email_address, city_address, user_level) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getFirstName());
            ps.setInt(5, user.getAge());
            ps.setString(6, user.getGender());
            ps.setString(7, user.getContactNumber());
            ps.setString(8, user.getEmailAddress());
            ps.setString(9, user.getCityAddress());
            ps.setInt(10, user.getUserLevel());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error adding user: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM tblusers";

        try (Connection conn = DbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User user = new User();

                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setLastName(rs.getString("last_name"));
                user.setFirstName(rs.getString("first_name"));
                user.setAge(rs.getInt("age"));
                user.setGender(rs.getString("gender"));
                user.setContactNumber(rs.getString("contact_number"));
                user.setEmailAddress(rs.getString("email_address"));
                user.setCityAddress(rs.getString("city_address"));
                user.setUserLevel(rs.getInt("user_level"));

                userList.add(user);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving users: " + e.getMessage());
        }
        return userList;
    }

    // ==========================================
    // NEW LOGIN METHOD IMPLEMENTATION
    // ==========================================
    @Override
    public User loginUser(String username, String password) {
        // SQL looks for a row where BOTH the username and password match exactly
        String sql = "SELECT * FROM tblusers WHERE username = ? AND password = ?";

        try (Connection conn = DbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                // If rs.next() is true, it means we found a match in the database!
                if (rs.next()) {
                    User user = new User();
                    user.setUserId(rs.getInt("user_id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setLastName(rs.getString("last_name"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setAge(rs.getInt("age"));
                    user.setGender(rs.getString("gender"));
                    user.setContactNumber(rs.getString("contact_number"));
                    user.setEmailAddress(rs.getString("email_address"));
                    user.setCityAddress(rs.getString("city_address"));
                    user.setUserLevel(rs.getInt("user_level"));

                    return user; // Return the fully loaded user object
                }
            }
        } catch (Exception e) {
            System.out.println("Error logging in: " + e.getMessage());
        }

        // If we reach here, no match was found (wrong password or username)
        return null;
    }

    @Override
    public User getUserById(int userId) {
        return null; // Implement later
    }

    @Override
    public boolean updateUser(User user) {
        return false; // Implement later
    }

    @Override
    public boolean deleteUser(int userId) {
        return false; // Implement later
    }
}