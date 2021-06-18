package org.example.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.example.App;
import org.example.config.UserConfig;

public class WelcomeScreenView implements Initializable {
    @FXML
    private void onCustomerClick(ActionEvent event) throws IOException {
        UserConfig.setCustomer();
        App.setRoot("login_screen");
    }
    @FXML
    private void onTranslatorClick(ActionEvent event) throws IOException {
        UserConfig.setTranslator();
        App.setRoot("login_screen");
    }

    private String getUserType() {
        return UserConfig.isCustomer() ? "Customer" : "Translator";
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
