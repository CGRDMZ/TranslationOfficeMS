package org.example.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import org.example.App;
import org.example.Utils;
import org.example.entity.User;
import org.example.model.UserModel;

import java.io.IOException;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerScreenView {

    @FXML
    private TextArea inputText;
    @FXML
    private  Label wordCount;
    @FXML
    private Label textPrice;
    @FXML
    private Label textDeadline;
    @FXML
    private Label selectedItemPrice;
    @FXML
    private Label selectedItemDeadline;
    @FXML
    private Button createJobButton;
    @FXML
    private Button pickFileButton;
    @FXML
    private TextField filePath;

    @FXML
    private void onCreateJobButtonClicked(ActionEvent event) {
        if(inputText.getText() != null){
            // use txt
            return;
        }
        else{
            Utils.showErrorMessage("Please enter a text or select a text file to create a job.");
        }
    }

    @FXML
    private void onPickFileButtonClicked(ActionEvent event){
        FileChooser fc= new FileChooser();

        // Text file filter
        fc.getExtensionFilters().addAll(new ExtensionFilter("Text Documents", "*.txt"));
        File selectedFile = fc.showOpenDialog(null);

        // ÇAĞRII BURAYI OKU:: Dosyanın adı ve konumunu tuttum burada ama işe yara mı bilmiyorum.
        // Belki database'de projelerin ismiyle tutarız dedim.

        String path = selectedFile.getAbsolutePath();
        String fileName = selectedFile.getName();

        // If a file has been selected
        if(selectedFile !=null){

            // Gets path of the selected file and shows it to user
            filePath.setText(selectedFile.getAbsolutePath());
        }
        else{
            Utils.showErrorMessage("Invalid File");
        }

        return;
    }


}
