package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.example.App;
import org.example.config.UserConfig;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreenController implements Initializable {
    @FXML
    private Label loginTitle;

    @FXML
    private void onLoginButtonClicked(ActionEvent e) throws IOException {
        if (UserConfig.isCustomer()) {
            App.setRoot("customer_screen");
        } else {
            System.out.println("navigating to translator screen...");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginTitle.textProperty().setValue(UserConfig.isCustomer() ? "Customer Login" : "Translator Login");
        System.out.println(loginTitle.getText());
    }
}
