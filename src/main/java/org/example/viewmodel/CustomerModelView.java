package org.example.viewmodel;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.entity.Job;
import org.example.entity.User;
import org.example.model.UserModel;
import org.example.utils.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerModelView {
    private static CustomerModelView SINGLETON;

    private SimpleStringProperty inputText;
    private SimpleIntegerProperty wordCount;
    private SimpleIntegerProperty textPrice;
    private SimpleStringProperty textDeadline;
    private SimpleIntegerProperty selectedItemPrice;
    private SimpleStringProperty selectedItemDeadline;
    private SimpleStringProperty filePath;
    private User user;



    private ObservableList<Job> customersPendingJobsList;

    private UserModel userModel;

    public CustomerModelView(UserModel userModel) {
        this.userModel = userModel;
        inputText = new SimpleStringProperty();
        wordCount = new SimpleIntegerProperty();
        textPrice = new SimpleIntegerProperty();
        textDeadline = new SimpleStringProperty(LocalDate.now().format(Utils.dateTimeFormatter));
        selectedItemPrice = new SimpleIntegerProperty();
        selectedItemDeadline = new SimpleStringProperty("D/M/YYYY");
        filePath = new SimpleStringProperty();
        user = UserModelView.getInstance().getUser();
        customersPendingJobsList = FXCollections.observableList(userModel.getCustomerJobs(user));

        textPrice.bind(wordCount.multiply(0.05));
        wordCount.addListener(((observableValue, oldV, newV) -> textDeadline.setValue(LocalDate.now().plusDays(Math.floorDiv(wordCount.get(), 500)).format(Utils.dateTimeFormatter))));
    }

    public static CustomerModelView getInstance() {
        if (SINGLETON == null) {
            SINGLETON = new CustomerModelView(new UserModel());
        }
        return SINGLETON;
    }

    public void createJob() {
        userModel.addUserJob(user, inputText.get(),textPrice.get(),user.getId());
    }

    public String getInputText() {
        return inputText.get();
    }

    public StringProperty inputTextProperty() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText.set(inputText);
    }

    public int getWordCount() {
        return wordCount.get();
    }

    public IntegerProperty wordCountProperty() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount.set(wordCount);
    }

    public int getTextPrice() {
        return textPrice.get();
    }

    public IntegerProperty textPriceProperty() {
        return textPrice;
    }

    public void setTextPrice(int textPrice) {
        this.textPrice.set(textPrice);
    }

    public String getTextDeadline() {
        return textDeadline.get();
    }

    public StringProperty textDeadlineProperty() {
        return textDeadline;
    }

    public void setTextDeadline(String textDeadline) {
        this.textDeadline.set(textDeadline);
    }

    public int getSelectedItemPrice() {
        return selectedItemPrice.get();
    }

    public IntegerProperty selectedItemPriceProperty() {
        return selectedItemPrice;
    }

    public void setSelectedItemPrice(int selectedItemPrice) {
        this.selectedItemPrice.set(selectedItemPrice);
    }

    public String getSelectedItemDeadline() {
        return selectedItemDeadline.get();
    }

    public StringProperty selectedItemDeadlineProperty() {
        return selectedItemDeadline;
    }

    public void setSelectedItemDeadline(String selectedItemDeadline) {
        this.selectedItemDeadline.set(selectedItemDeadline);
    }

    public String getFilePath() {
        return filePath.get();
    }

    public StringProperty filePathProperty() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath.set(filePath);
    }

    public ObservableList<Job> getCustomersPendingJobsList() {
        return customersPendingJobsList;
    }

    public void setCustomersPendingJobsList(ObservableList<Job> customersPendingJobsList) {
        this.customersPendingJobsList = customersPendingJobsList;
    }
}
