package org.example.viewmodel;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.entity.Job;
import org.example.entity.User;
import org.example.model.UserModel;

import java.sql.SQLException;

public class TranslatorModelView {
    private static TranslatorModelView SINGLETON;

    private SimpleStringProperty sourceText;
    private SimpleStringProperty translatedText;
    private SimpleObjectProperty<Job> selectedJob;
    private User user;

    private ObservableList<Job> translatorsPendingJobs;
    private ObservableList<Job> currentAvailableJobs;

    private UserModel userModel;

    public TranslatorModelView(UserModel userModel) {
        this.userModel = userModel;
        user = UserModelView.getInstance().getUser();
        sourceText = new SimpleStringProperty("");
        translatedText = new SimpleStringProperty("");
        selectedJob = new SimpleObjectProperty<>();

        translatorsPendingJobs = FXCollections.observableList(userModel.getTranslatorJobs(user));
        currentAvailableJobs = FXCollections.observableList(userModel.getCurrentAvailableJobs());

        selectedJob.addListener((obs, oldV, newV) -> {
            System.out.println(newV);
            Job job = userModel.getJobById(newV.getId());
            sourceText.setValue(job.getTextToTranslate());
            translatedText.setValue(job.getTranslatedText());
        });
    }


    public static TranslatorModelView getInstance() {
        if (SINGLETON == null) {
            SINGLETON = new TranslatorModelView(new UserModel());
        }
        return SINGLETON;
    }

    public void refreshTables() {
        translatorsPendingJobs.setAll(userModel.getTranslatorJobs(user));
        currentAvailableJobs.setAll(userModel.getCurrentAvailableJobs());
    }

    public void acceptJob(Job job) throws SQLException {
        userModel.assignJobToTranslator(user, job);
    }

    public boolean saveText() throws SQLException {
        return userModel.saveTranslation(selectedJob.get(), translatedText.get());
    }

    public void submitText() throws SQLException {
        userModel.submitAJob(selectedJob.get());
    }

    public Job getSelectedJob() {
        return selectedJob.get();
    }

    public SimpleObjectProperty<Job> selectedJobProperty() {
        return selectedJob;
    }

    public void setSelectedJob(Job selectedJob) {
        this.selectedJob.set(selectedJob);
    }

    public String getSourceText() {
        return sourceText.get();
    }

    public SimpleStringProperty sourceTextProperty() {
        return sourceText;
    }

    public void setSourceText(String sourceText) {
        this.sourceText.set(sourceText);
    }

    public String getTranslatedText() {
        return translatedText.get();
    }

    public SimpleStringProperty translatedTextProperty() {
        return translatedText;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText.set(translatedText);
    }

    public ObservableList<Job> getTranslatorsPendingJobs() {
        return translatorsPendingJobs;
    }

    public void setTranslatorsPendingJobs(ObservableList<Job> translatorsPendingJobs) {
        this.translatorsPendingJobs = translatorsPendingJobs;
    }

    public ObservableList<Job> getCurrentAvailableJobs() {
        return currentAvailableJobs;
    }

    public void setCurrentAvailableJobs(ObservableList<Job> currentAvailableJobs) {
        this.currentAvailableJobs = currentAvailableJobs;
    }
}
