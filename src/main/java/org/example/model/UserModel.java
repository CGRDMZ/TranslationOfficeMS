package org.example.model;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.UpdateBuilder;
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


    private final ConnectionSource connectionSource;

    private Dao<User,Integer> userDao;
    private Dao<Job,Integer> jobDao;

    public UserModel() {
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
    }

    public User getUserById(int id) throws SQLException {
        return userDao.queryForId(id);
    }

    public User getUserByUsername(String username) throws SQLException {
        List<User> result = userDao.queryForEq("username", username);
        if (result.size() > 1) {
            Utils.showInfoMessage("There is a inconsistency in the database. (more than one user with the same name.");
        }
        return result.size() == 0 ? null : result.get(0);

    }

    public List<Job> getCustomerJobs(User user) {
        User result = null;
        try {
            result = userDao.queryForSameId(user);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        if (result == null) {
            System.out.println("Error: user not found.");
            return null;
        }

        return new ArrayList<>(result.getCustomerJobs());
    }

    public Job getJobById(int id) {
        try {
            return jobDao.queryForId(id);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("Error: while getting job with id.");
            return null;
        }
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
    }

    public List<Job> getCurrentAvailableJobs() {
        try {
            return jobDao.queryBuilder()
                    .where()
                    .isNull("assignedTo_id")
                    .and()
                    .not().eq("translationCompleted", true)
                    .query();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("Error: while getting available Jobs.");
            return null;
        }
    }

    public List<Job> getTranslatorJobs(User user) {
        return new ArrayList<>(user.getTranslationJobs());
    }


    public boolean assignJobToTranslator(User user, Job job) throws SQLException {
        if (job == null) return false;
        job.setAssignedTo(user);
        return jobDao.update(job) == 1;
    }

    public boolean saveTranslation(Job job, String newTranslationText) throws SQLException {
        if (job.isTranslationCompleted()) return false;
        job.setTranslatedText(newTranslationText);
        return jobDao.update(job) == 1;
    }

    public boolean submitAJob(Job job) throws SQLException {
        job.setTranslationCompleted(true);
        return jobDao.update(job) == 1;
    }

    public User login(String username, String password) throws SQLException {
        User user = getUserByUsername(username);
        System.out.println(user);
        if (user == null) return null;
        return user.getPassword().equals(password) ? user : null;
    }


}
