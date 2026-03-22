package com.app.main;

//import exception.ValidationException;
import java.sql.Connection;
import com.app.util.DbConnection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
//import util.InputUtil;
//import util.PasswordUtil;

public class TestConnection {
    public static void main(String[] args) {
        Connection conn = DbConnection.connect();

        if (conn != null) {
            System.out.println("Database Connected Successfully!");
        } else {
            System.out.println("Connection Failed!");
        }
            /* InputUtil Test
            int age = InputUtil.getInt("Enter age: ");
            System.out.println("Age: " + age);
            */

            /* PasswordUtil Test
            String raw = "admin123";
            String hashed = PasswordUtil.hashPassword(raw);

            System.out.println("Raw: " + raw);
            System.out.println("Hashed: " + hashed);

            System.out.println("Match: " + PasswordUtil.matchPassword("admin123", hashed));
            */
            /* Exception Test
            try {
            throw new ValidationException("Test validation error");
            } catch (ValidationException e) {
            System.out.println(e.getMessage());
            }
            */
    }
}
