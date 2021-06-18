package org.example.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Job {
    private int id;
    private String textToTranslate;
    private String translatedText;
    private String translatedFromLanguage;
    private String translatedToLanguage;
    private boolean translationCompleted;
    private int price;
    private String issuedAt;
    private String approximatedDeadline;
    private int issuedByUser;
    private int assignedTo;


    public static List<Job> ResultSetToJobList(ResultSet rs) throws SQLException {
        List<Job> jobList = new ArrayList<>();

        while (rs.next()) {
            Job job = new Job()
                    .setId(rs.getInt("id"))
                    .setTextToTranslate(rs.getString("textToTranslate"))
                    .setAssignedTo(rs.getInt("assignedTo"))
                    .setIssuedByUser(rs.getInt("owner"))
                    .setIssuedAt(rs.getString("issuedAt"));
            System.out.println(job + "yep");
            jobList.add(job);
        }
        return jobList;
    }

    public int getId() {
        return id;
    }

    public Job setId(int id) {
        this.id = id;
        return this;
    }

    public String getTextToTranslate() {
        return textToTranslate;
    }

    public Job setTextToTranslate(String textToTranslate) {
        this.textToTranslate = textToTranslate;
        return this;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public Job setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
        return this;
    }

    public String getTranslatedFromLanguage() {
        return translatedFromLanguage;
    }

    public Job setTranslatedFromLanguage(String translatedFromLanguage) {
        this.translatedFromLanguage = translatedFromLanguage;
        return this;
    }

    public String getTranslatedToLanguage() {
        return translatedToLanguage;
    }

    public Job setTranslatedToLanguage(String translatedToLanguage) {
        this.translatedToLanguage = translatedToLanguage;
        return this;
    }

    public boolean isTranslationCompleted() {
        return translationCompleted;
    }

    public Job setTranslationCompleted(boolean translationCompleted) {
        this.translationCompleted = translationCompleted;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public Job setPrice(int price) {
        this.price = price;
        return this;
    }

    public String getIssuedAt() {
        return issuedAt;
    }

    public Job setIssuedAt(String issuedAt) {
        this.issuedAt = issuedAt;
        return this;
    }

    public String getApproximatedDeadline() {
        return approximatedDeadline;
    }

    public Job setApproximatedDeadline(String approximatedDeadline) {
        this.approximatedDeadline = approximatedDeadline;
        return this;
    }

    public int getIssuedByUser() {
        return issuedByUser;
    }

    public Job setIssuedByUser(int issuedByUser) {
        this.issuedByUser = issuedByUser;
        return this;
    }

    public int getAssignedTo() {
        return assignedTo;
    }

    public Job setAssignedTo(int assignedTo) {
        this.assignedTo = assignedTo;
        return this;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", textToTranslate='" + textToTranslate.substring(0, 12) + '\'' +
                ", translatedText='" + (translatedText == null ? null : translatedText.substring(0, 12)) + '\'' +
                ", translatedFromLanguage='" + translatedFromLanguage + '\'' +
                ", translatedToLanguage='" + translatedToLanguage + '\'' +
                ", translationCompleted=" + translationCompleted +
                ", price=" + price +
                ", issuedAt=" + issuedAt +
                ", approximatedDeadline=" + approximatedDeadline +
                ", issuedByUser=" + issuedByUser +
                ", assignedTo=" + assignedTo +
                '}';
    }
}
