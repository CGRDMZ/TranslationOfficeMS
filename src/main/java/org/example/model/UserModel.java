package org.example.model;

import org.example.config.DBConnection;
import org.example.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {

    private static UserModel SINGLETON;

    private Connection dbConnection;
    private User loggedInUser;

    private UserModel() {
        this.dbConnection = DBConnection.connectDB();
    }

    public static UserModel getInstance() {
        if (SINGLETON == null) {
            SINGLETON = new UserModel();
        }
        return SINGLETON;
    }

    public boolean register(String username, String password, boolean isCustomer, boolean isTranslator) throws Exception {
        User newUser = new User()
                .setUsername(username)
                .setPassword(password)
                .setCustomer(isCustomer)
                .setTranslator(isTranslator);

        if (newUser == null) return false;
        if (newUser.getUsername() == null) throw new Exception("Can not insert a new user without a username");
        if (newUser.getPassword() == null) throw new Exception("Can not insert a new user without a password");

        String sql = "insert into Users (username, password, isCustomer, isTranslator) values (?,?,?,?)";

        PreparedStatement statement = dbConnection.prepareStatement(sql);
        statement.setString(1, newUser.getUsername());
        statement.setString(2, newUser.getPassword());
        statement.setBoolean(3, newUser.isCustomer());
        statement.setBoolean(4, newUser.isTranslator());
        try {
            return statement.execute();
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
            return User.ResultSetToUser(result);
        } catch (SQLException sqlException) {
            return null;
        }
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
