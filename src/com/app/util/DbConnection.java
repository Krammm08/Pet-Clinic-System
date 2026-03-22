package com.app.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
    // UPDATED: Changed from petclinic_db to dbpetclinic
    private static final String URL = "jdbc:mysql://localhost:3306/dbpetclinic";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Default XAMPP password is blank

    public static Connection connect() {
        try {
            // Ensure the driver is loaded
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            // This will tell you EXACTLY why it failed in your console
            System.out.println("\t[DATABASE ERROR] " + e.getMessage());
            return null;
        }
    }
}