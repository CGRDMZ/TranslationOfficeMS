package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection connectDB() {
        try {
            String uri = "jdbc:sqlite:user.db";
            return DriverManager.getConnection(uri);
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database.");
            System.out.println(e.getMessage());
            return null;
        }
    }
}
