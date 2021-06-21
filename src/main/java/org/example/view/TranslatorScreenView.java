package org.example.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.example.App;
import org.example.entity.Job;
import org.example.utils.Utils;
import org.example.viewmodel.TranslatorModelView;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class TranslatorScreenView implements Initializable{

    @FXML
    private TableView<Job> translatorJobList;
    @FXML
    private TableColumn<Job, Integer> translatorJobListID;
    @FXML
    private TableColumn<Job, Integer> translatorJobListPrice;
    @FXML
    private TableColumn<Job, String> translatorJobListAssignedTo;
    @FXML
    private TableColumn<Job, Boolean> translatorJobListStatus;

    @FXML
    private TableView<Job> translatorJobDetails;
    @FXML
    private TableColumn<Job, Integer> translatorJobDetailsID;
    @FXML
    private TableColumn<Job, Integer> translatorJobDetailsPrice;
    @FXML
    private TableColumn<Job, String> translatorJobDetailsCustomer;
    @FXML
    private TableColumn<Job, Date> translatorJobDetailsDeadline;
    @FXML
    private TableColumn<Job, String> translatorJobDetailsText;
    @FXML
    private Button refreshButton;
    @FXML
    private Label translatorWallet;
    @FXML
    private Button acceptJobButton;

    private TranslatorModelView translatorModelView;

    @FXML
    private void onAcceptJobButtonClicked(ActionEvent event){
        try {
            translatorModelView.acceptJob(translatorJobDetails.getSelectionModel().getSelectedItem());
            translatorModelView.refreshTables();
        } catch (SQLException sqlException) {
            Utils.showErrorMessage("Job couldn't assigned.");
            sqlException.printStackTrace();
        }
    }

    @FXML
    private void onRefreshButtonClicked(ActionEvent event){
        translatorModelView.refreshTables();
    }

    @FXML
    private void onPendingJobListClicked(MouseEvent event) throws IOException {
        if (event.getClickCount() == 2 && event.getButton().equals(MouseButton.PRIMARY)) {
            App.setRoot("translation_screen");
        }
    }

    private void setBindings() {

    }

    private void setTable() {
        // detailed table.

        translatorJobDetailsID.setCellValueFactory(new PropertyValueFactory<Job, Integer>("id"));
        translatorJobDetailsPrice.setCellValueFactory(new PropertyValueFactory<Job, Integer>("price"));
        translatorJobDetailsCustomer.setCellValueFactory((jobUserCellDataFeatures) ->
            new SimpleStringProperty(jobUserCellDataFeatures.getValue().getIssuedByUser().getUsername())
        );
        translatorJobDetailsDeadline.setCellValueFactory(new PropertyValueFactory<Job, Date>("approximatedDeadline"));
        translatorJobDetailsText.setCellValueFactory(jobStringCellDataFeatures -> {
            if (jobStringCellDataFeatures.getValue() == null) return null;
            if (jobStringCellDataFeatures.getValue().getTextToTranslate() == null) return null;
            if (jobStringCellDataFeatures.getValue().getTextToTranslate().length() <= 28)
                return new SimpleStringProperty(jobStringCellDataFeatures.getValue().getTextToTranslate());
            return new SimpleStringProperty(jobStringCellDataFeatures.getValue().getTextToTranslate().substring(0, 28));
        });

        translatorJobDetails.itemsProperty().setValue(translatorModelView.getCurrentAvailableJobs());


        // small table with users pending jobs.
        translatorJobListID.setCellValueFactory(new PropertyValueFactory<Job, Integer>("id"));
        translatorJobListPrice.setCellValueFactory(new PropertyValueFactory<Job, Integer>("price"));
        translatorJobListAssignedTo.setCellValueFactory((jobUserCellDataFeatures ->
            new SimpleStringProperty(jobUserCellDataFeatures.getValue().getAssignedTo().getUsername())
        ));
        translatorJobListStatus.setCellValueFactory(new PropertyValueFactory<Job, Boolean>("translationCompleted"));

        translatorJobList.itemsProperty().setValue(translatorModelView.getTranslatorsPendingJobs());

        translatorJobList.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            translatorModelView.setSelectedJob(newV);
        });

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.translatorModelView = TranslatorModelView.getInstance();
        setBindings();
        setTable();
    }
}
