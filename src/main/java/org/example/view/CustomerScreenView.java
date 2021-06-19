package org.example.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import org.example.utils.Utils;

import java.io.File;

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
        System.out.println();
        if(!inputText.getText().trim().equals("")){
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


        if (selectedFile == null) {
            Utils.showErrorMessage("Invalid File");
            return;
        }

        String path = selectedFile.getAbsolutePath();
        String fileName = selectedFile.getName();


        filePath.setText(path);

        return;
    }


}
