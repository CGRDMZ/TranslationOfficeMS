package org.example.model;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import javafx.collections.ObservableList;
import org.example.config.DBConnection;
import org.example.entity.Job;
import org.example.entity.User;
import org.example.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class UserModel {


    private final Connection dbConnection;
    private final ConnectionSource connectionSource;
    private Dao<User,Integer> userDao;
    private Dao<Job,Integer> jobDao;

    public UserModel() {
        this.dbConnection = DBConnection.connectDB();
        this.connectionSource = DBConnection.getConnectionSource();
        try {
            userDao = DaoManager.createDao(connectionSource, User.class);
            jobDao = DaoManager.createDao(connectionSource, Job.class);
        } catch (SQLException sqlException) {
            System.out.println("Error: while creating dao.");
            sqlException.printStackTrace();
        }

    }


    public boolean register(String username, String password, boolean isCustomer, boolean isTranslator) throws SQLException {
        User searchUser = getUserByUsername(username);
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
        if (newUser.getPassword().length() < 5) return false;
        if (newUser.getUsername().length() < 5) return false;

        try {
            return userDao.create(newUser) == 1;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }

//        String sql = "insert into Users (username, password, isCustomer, isTranslator) values (?,?,?,?)";
//
//        try {
//            PreparedStatement statement = dbConnection.prepareStatement(sql);
//            statement.setString(1, newUser.getUsername());
//            statement.setString(2, newUser.getPassword());
//            statement.setBoolean(3, newUser.isCustomer());
//            statement.setBoolean(4, newUser.isTranslator());
//            System.out.println(statement.executeUpdate());
//            return true;
//        } catch (SQLException e) {
//            return false;
//        }
    }

    public User getUserById(int id) throws SQLException {
        return userDao.queryForId(id);
    }

    public User getUserByUsername(String username) throws SQLException {
        List<User> result = userDao.queryForEq("username", username);
        if (result.size() > 1) {
            Utils.showInfoMessage("There is a inconsistency in the database. (more than one user with the same name");
        }
        return result.size() == 0 ? null : result.get(0);

    }

    public List<Job> getCustomerJobs(User user) throws SQLException {

        User result = userDao.queryForSameId(user);
        if (result == null) {
            System.out.println("Error: user not found.");
            return null;
        }

        return new ArrayList<>(result.getCustomerJobs());
    }

    public Job addCustomerJob(int userID, String textToTranslate, int price, Date approximateDeadline, Date issuedAt) throws SQLException {
        User user = getUserById(userID);
        Job newJob = new Job()
                .setTextToTranslate(textToTranslate)
                .setPrice(price)
                .setIssuedByUser(user)
                .setApproximatedDeadline(approximateDeadline)
                .setIssuedAt(issuedAt);

        return jobDao.create(newJob) == 1 ? newJob : null;
//        String sql = "insert into Jobs (textToTranslate, price," +
//                "owner, issuedAt) values (?, ?, ?, ?)";
//        PreparedStatement statement;
//        System.out.println();
//
//        try {
//            statement = dbConnection.prepareStatement(sql);
//            statement.setString(1, textToTranslate);
//            statement.setInt(2, price);
//            statement.setInt(3, owner);
//            statement.setString(4, LocalDate.now().format(Utils.dateTimeFormatter));
//
//            int i = statement.executeUpdate();
//            if (i != 1) {
//                System.out.println("Error: Job failed to insert.");
//                return null;
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Error: inserting a new job failed." + e.getLocalizedMessage());
//            return null;
//        }
    }

    public User login(String username, String password) throws SQLException {
        User user = getUserByUsername(username);
        System.out.println(user);
        if (user == null) return null;
        return user.getPassword().equals(password) ? user : null;
    }


}
