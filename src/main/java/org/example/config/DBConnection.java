package org.example.config;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.example.entity.Job;
import org.example.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection connectDB() {
        try {
            String uri = "jdbc:sqlite:user.db";
            JdbcPooledConnectionSource connectionSource = new JdbcPooledConnectionSource(uri);
            return DriverManager.getConnection(uri);
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database.");
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static ConnectionSource getConnectionSource() {
        String uri = "jdbc:sqlite:user.db";
        try {
            JdbcPooledConnectionSource connectionSource = new JdbcPooledConnectionSource(uri);
            TableUtils.createTableIfNotExists(connectionSource, Job.class);
            TableUtils.createTableIfNotExists(connectionSource, User.class);
            return connectionSource;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }
}
