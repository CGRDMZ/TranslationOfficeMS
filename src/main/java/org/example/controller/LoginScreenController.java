package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.App;
import org.example.config.UserConfig;
import org.example.domain.User;
import org.example.model.UserModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreenController implements Initializable {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label loginTitle;
    @FXML
    private Label errorMessage;

    private UserModel userModel;

    @FXML
    private void onLoginButtonClicked(ActionEvent e) throws IOException {
        User loginUser = userModel.login(usernameField.getText(), passwordField.getText());
        if (loginUser == null) {
            errorMessage.textProperty().setValue("Wrong username or password.");
            errorMessage.visibleProperty().setValue(true);
            return;
        };
        if (UserConfig.isCustomer() && loginUser.isCustomer()) {
            App.setRoot("customer_screen");
        } else if (!UserConfig.isCustomer() && loginUser.isTranslator()){
            System.out.println("navigating to translator screen...");
        }
    }

    @FXML
    private void onRegisterButtonClicked(ActionEvent e) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userModel = UserModel.getInstance();
        loginTitle.textProperty().setValue(UserConfig.isCustomer() ? "Customer Login" : "Translator Login");
        System.out.println(loginTitle.getText());
    }
}
