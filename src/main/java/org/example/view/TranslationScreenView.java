package org.example.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.App;
import org.example.utils.Utils;
import org.example.viewmodel.CustomerModelView;
import org.example.viewmodel.TranslatorModelView;
import org.example.viewmodel.UserModelView;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TranslationScreenView implements Initializable {

    @FXML
    private TextArea sourceTextArea;
    @FXML
    private TextArea translatedTextArea;
    @FXML
    private Button backButton;
    @FXML
    private Button submitButton;
    @FXML
    private Button saveButton;

    private CustomerModelView customerModelView;
    private TranslatorModelView translatorModelView;
    private UserModelView userModelView;

    @FXML
    public void onSubmitButtonClicked(ActionEvent event){
        try {
            translatorModelView.saveText();
            translatorModelView.submitText();
            Utils.showInfoMessage("Text is submitted successfully. (No more editing.)");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @FXML
    public void onSaveButtonClicked(ActionEvent event){
        try {
            if (translatorModelView.saveText()) {
                Utils.showInfoMessage("Text is saved.");
            } else {
                Utils.showErrorMessage("Failed to save the text.");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @FXML
    public void onBackButtonClicked(ActionEvent event) throws IOException {
        if (userModelView.isTranslator()) {
            App.setRoot("translator_screen");
        } else {
            App.setRoot("customer_screen");
        }
    }

    private void setBindings() {
        if (userModelView.isTranslator()) {
            sourceTextArea.textProperty().bindBidirectional(translatorModelView.sourceTextProperty());
            translatedTextArea.textProperty().bindBidirectional(translatorModelView.translatedTextProperty());
        } else {
            sourceTextArea.textProperty().setValue(customerModelView.getSelectedJob().getTextToTranslate());
            translatedTextArea.textProperty().setValue(customerModelView.getSelectedJob().getTranslatedText());
        }
    }

    private void setButtons() {
        if (!userModelView.isTranslator()) {
            saveButton.setVisible(false);
            submitButton.setVisible(false);
            translatedTextArea.editableProperty().setValue(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        translatorModelView = TranslatorModelView.getInstance();
        userModelView = UserModelView.getInstance();
        customerModelView = CustomerModelView.getInstance();
        setBindings();
        setButtons();
    }
}
