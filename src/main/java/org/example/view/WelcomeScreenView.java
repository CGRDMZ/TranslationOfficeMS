package org.example.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.example.App;
import org.example.viewmodel.UserModelView;

public class WelcomeScreenView implements Initializable {
    UserModelView userModelView;

    @FXML
    private void onCustomerClick(ActionEvent event) throws IOException {
        App.setRoot("login_screen");
        userModelView.isTranslatorProperty().setValue(false);
    }
    @FXML
    private void onTranslatorClick(ActionEvent event) throws IOException {
        userModelView.isTranslatorProperty().setValue(true);
        App.setRoot("login_screen");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userModelView = UserModelView.getInstance();
    }
}
