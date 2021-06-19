package org.example.entity;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "Jobs")
public class Job {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(canBeNull = false)
    private String textToTranslate;
    @DatabaseField()
    private String translatedText;
    @DatabaseField()
    private String translatedFromLanguage;
    @DatabaseField()
    private String translatedToLanguage;
    @DatabaseField()
    private boolean translationCompleted;
    @DatabaseField(canBeNull = false)
    private int price;
    @DatabaseField(dataType = DataType.DATE)
    private Date issuedAt;
    @DatabaseField(dataType = DataType.DATE)
    private Date approximatedDeadline;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
    private User issuedByUser;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
    private User assignedTo;

    public Job() {
    }

//    public static List<Job> ResultSetToJobList(ResultSet rs) throws SQLException {
//        List<Job> jobList = new ArrayList<>();
//
//        while (rs.next()) {
//            Job job = new Job()
//                    .setId(rs.getInt("id"))
//                    .setTextToTranslate(rs.getString("textToTranslate"))
//                    .setAssignedTo(rs.getInt("assignedTo"))
//                    .setIssuedByUser(rs.getInt("owner"))
//                    .setIssuedAt(rs.getString("issuedAt"));
//            System.out.println(job + "yep");
//            jobList.add(job);
//        }
//        return jobList;
//    }

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

    public User getIssuedByUser() {
        return issuedByUser;
    }

    public Job setIssuedByUser(User issuedByUser) {
        this.issuedByUser = issuedByUser;
        return this;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public Job setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
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

    public Date getIssuedAt() {
        return issuedAt;
    }

    public Job setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
        return this;
    }

    public Date getApproximatedDeadline() {
        return approximatedDeadline;
    }

    public Job setApproximatedDeadline(Date approximatedDeadline) {
        this.approximatedDeadline = approximatedDeadline;
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
