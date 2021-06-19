package org.example.entity;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@DatabaseTable(tableName = "User")
public class User {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField()
    private String username;
    @DatabaseField()
    private String password;
    @DatabaseField()
    private boolean isCustomer;
    @DatabaseField()
    private boolean isTranslator;
    @ForeignCollectionField()
    private ForeignCollection<Job> jobs;


    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public ForeignCollection<Job> getJobs() {
        return jobs;
    }

    public void setJobs(ForeignCollection<Job> jobs) {
        this.jobs = jobs;
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
