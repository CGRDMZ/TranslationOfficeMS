package org.example.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class User {
    private int id;
    private String username;
    private String password;
    private boolean isCustomer;
    private boolean isTranslator;
    private List<Job> jobs;

    public static User ResultSetToUser(ResultSet rs) {
        try {
            return new User()
                    .setId(rs.getInt("id"))
                    .setUsername(rs.getString("username"))
                    .setPassword(rs.getString("password"))
                    .setCustomer(rs.getBoolean("isCustomer"))
                    .setTranslator(rs.getBoolean("isTranslator"));
        } catch (SQLException sqlException) {
            return null;
        }
    }

    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public User setJobs(List<Job> jobs) {
        this.jobs = jobs;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean isCustomer() {
        return isCustomer;
    }

    public User setCustomer(boolean customer) {
        isCustomer = customer;
        return this;
    }

    public boolean isTranslator() {
        return isTranslator;
    }

    public User setTranslator(boolean translator) {
        isTranslator = translator;
        return this;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isCustomer=" + isCustomer +
                ", isTranslator=" + isTranslator +
                ", jobs=" + jobs.toString() +
                '}';
    }
}
