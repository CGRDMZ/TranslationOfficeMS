package org.example.model;

import org.example.config.DBConnection;
import org.example.entity.Job;
import org.example.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class UserModel {


    private final Connection dbConnection;
    private User loggedInUser;

    public UserModel() {
        this.dbConnection = DBConnection.connectDB();
    }


    public boolean register(String username, String password, boolean isCustomer, boolean isTranslator) {
        User searchUser = getUser(username);
        if (searchUser != null) {
            return false;
        }

        User newUser = new User()
                .setUsername(username)
                .setPassword(password)
                .setCustomer(isCustomer)
                .setTranslator(isTranslator);

        if (newUser == null) return false;
        if (newUser.getUsername() == null) return false;
        if (newUser.getPassword() == null) return false;

        String sql = "insert into Users (username, password, isCustomer, isTranslator) values (?,?,?,?)";

        try {
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            statement.setString(1, newUser.getUsername());
            statement.setString(2, newUser.getPassword());
            statement.setBoolean(3, newUser.isCustomer());
            statement.setBoolean(4, newUser.isTranslator());
            System.out.println(statement.execute());
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public User getUser(String username) {
        String sql = "select * from Users where username = ?";
        PreparedStatement statement;
        try {
            statement = dbConnection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();
            User user = User.ResultSetToUser(result);
            assert user != null;
            return user.setJobs(getCustomerJobs(user));

        } catch (SQLException sqlException) {
            System.out.println("Error: problem while creating user.");
            return null;
        }
    }

    public List<Job> getCustomerJobs(User user) {
        String sql = "select * from Jobs where owner = ?";
        PreparedStatement statement;

        try {
            statement = dbConnection.prepareStatement(sql);
            statement.setInt(1, user.getId());

            ResultSet rs = statement.executeQuery();
            return Job.ResultSetToJobList(rs);

        } catch (SQLException e) {
            System.out.println("Error: getting the jobs failed.");
            return null;
        }
    }

    public Job addUserJob(User user, Job job) {
        String sql = "insert into Jobs (textToTranslate, price," +
                "owner, issuedAt) values (?, ?, ?, ?, ?)";
        PreparedStatement statement;

        try {
            statement = dbConnection.prepareStatement(sql);
            statement.setString(1, job.getTextToTranslate());
            statement.setInt(2, job.getPrice());
            statement.setInt(3, job.getIssuedByUser());
            statement.setString(4, LocalDate.now().toString());

            int i = statement.executeUpdate();
            if (i != 1) {
                System.out.println("Error: Job failed to insert.");
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Error: inserting a new job failed.");
            return null;
        }
        return null;
    }

    public User login(String username, String password) {
        if (loggedInUser != null) logout();
        User user = getUser(username);
        System.out.println(user);
        if (user == null) return null;
        loggedInUser = user;
        return user.getPassword().equals(password) ? user : null;
    }

    public void logout() {
        loggedInUser = null;
    }

}
