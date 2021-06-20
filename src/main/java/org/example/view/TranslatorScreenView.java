package org.example.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.App;
import org.example.utils.Utils;
import org.example.viewmodel.UserModelView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TranslatorScreenView {

    @FXML
    private TableView translatorJobDetails;
    @FXML
    private TableView translatorJobList;
    @FXML
    private Label translatorWallet;
    @FXML
    private TableColumn translatorJobListID;
    @FXML
    private TableColumn translatorJobListPrice;
    @FXML
    private TableColumn translatorJobListAssignedTo;
    @FXML
    private TableColumn translatorJobListStatus;
    @FXML
    private TableColumn translatorJobDetailsID;
    @FXML
    private TableColumn translatorJobDetailsPrice;
    @FXML
    private TableColumn translatorJobDetailsCustomer;
    @FXML
    private TableColumn translatorJobDetailsDeadline;
    @FXML
    private TableColumn translatorJobDetailsText;
    @FXML
    private Button refreshButton;
    @FXML
    private Button acceptJobButton;

    @FXML
    private void onAcceptJobButtonClicked(ActionEvent event){

    }

    @FXML
    private void onRefreshButtonClicked(ActionEvent event){

    }


}
