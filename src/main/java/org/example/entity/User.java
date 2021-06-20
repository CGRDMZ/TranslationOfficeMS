package org.example.entity;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

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
    @ForeignCollectionField(foreignFieldName = "issuedByUser")
    private ForeignCollection<Job> customerJobs;
    @ForeignCollectionField(foreignFieldName = "assignedTo")
    private ForeignCollection<Job> translationJobs;


    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public ForeignCollection<Job> getTranslationJobs() {
        return translationJobs;
    }

    public User setTranslationJobs(ForeignCollection<Job> translationJobs) {
        this.translationJobs = translationJobs;
        return this;
    }

    public ForeignCollection<Job> getCustomerJobs() {
        return customerJobs;
    }

    public void setCustomerJobs(ForeignCollection<Job> customerJobs) {
        this.customerJobs = customerJobs;
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
                ", jobs=" + customerJobs.toString() +
                '}';
    }
}
