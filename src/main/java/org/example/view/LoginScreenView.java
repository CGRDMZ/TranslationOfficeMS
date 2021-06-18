package org.example.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.App;
import org.example.Utils;
import org.example.viewmodel.UserModelView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.control.Alert.*;

public class LoginScreenView implements Initializable {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label loginTitle;
    @FXML
    private Label errorMessage;

    private UserModelView userModelView;

    @FXML
    private void onLoginButtonClicked(ActionEvent e) throws IOException {
        if (userModelView.login()) {
            Utils.showInfoMessage("login succesfull.");
            if (userModelView.isTranslator()) {
                Utils.showInfoMessage("navigating to translator screen...");
            } else {
                App.setRoot("customer_screen");
            }
        } else {
            Utils.showErrorMessage("login failed.");
        }
    }

    private void setBindings() {
        userModelView.usernameProperty().bind(usernameField.textProperty());
        userModelView.passwordProperty().bind(passwordField.textProperty());
        loginTitle.textProperty().setValue(userModelView.isTranslator() ? "Translator Login" : "Customer Login");
    }

    @FXML
    private void onRegisterButtonClicked(ActionEvent e) {
        if (userModelView.register()) {
            Utils.showInfoMessage("User is created successfully.");
        } else {
            Utils.showErrorMessage("Something went wrong with the creation of the new account.");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userModelView = UserModelView.getInstance();
        setBindings();
    }
}
