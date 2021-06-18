package org.example.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.App;
import org.example.config.UserConfig;
import org.example.entity.User;
import org.example.model.UserModel;
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
        User loginUser = userModelView.login(usernameField.getText(), passwordField.getText());
        if (loginUser == null) {
            showErrorMessage("Username or password is incorrect.");
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
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (username.length() < 5) {
            showErrorMessage("Username must be at least 5 characters.");
            return;
        }
        if (password.length() < 5) {
            showErrorMessage("Password must be at least 5 characters.");
            return;
        }

        if (userModelView.register(username, password, UserConfig.isCustomer(), !UserConfig.isCustomer())) {
            showInfoMessage("User is created successfully.");
        } else {
            showErrorMessage("Something went wrong with the creation of the new account.");
        }
    }

    private void showErrorMessage(String message) {
//        errorMessage.textProperty().setValue(message);
//        errorMessage.setStyle("-fx-background-color: #ff0000;");
//        errorMessage.visibleProperty().setValue(true);
        Alert alert = new Alert(AlertType.ERROR, message, ButtonType.OK);
        alert.show();
    }
    private void showInfoMessage(String message) {
//        errorMessage.textProperty().setValue(message);
//        errorMessage.setStyle("-fx-background-color: #00ff00;");
//        errorMessage.visibleProperty().setValue(true);
        Alert alert = new Alert(AlertType.INFORMATION, message, ButtonType.OK);
        alert.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userModel = UserModel.getInstance();
        loginTitle.textProperty().setValue(UserConfig.isCustomer() ? "Customer Login" : "Translator Login");
        System.out.println(loginTitle.getText());
    }
}
