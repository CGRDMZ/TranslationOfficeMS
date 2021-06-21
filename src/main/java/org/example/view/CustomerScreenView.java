package org.example.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.converter.CurrencyStringConverter;
import javafx.util.converter.NumberStringConverter;
import org.example.App;
import org.example.entity.Job;
import org.example.utils.Utils;
import org.example.viewmodel.CustomerModelView;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class CustomerScreenView implements Initializable {

    @FXML
    public TableView<Job> usersPendingJobsListView;
    @FXML
    public TableColumn<Job, Integer> itemName;
    @FXML
    public TableColumn<Job, String> itemStatus;
    @FXML
    public TableColumn<Job, String> itemAssignedTo;

    @FXML
    private TextArea inputText;
    @FXML
    private Label wordCount;
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

    private CustomerModelView customerModelView;

    @FXML
    private void onCreateJobButtonClicked(ActionEvent event) {
        if (inputText.getText() != null && !inputText.getText().trim().equals("")) {
            try {
                customerModelView.createJob();
                customerModelView.refreshPendingJobs();
                customerModelView.clearInput();
                Utils.showInfoMessage("Job has been created.");
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                Utils.showErrorMessage("Error: while creating the job.");
            }
        } else {
            Utils.showErrorMessage("Please enter a text or select a text file to create a job.");
        }
    }

    @FXML
    private void onPickFileButtonClicked(ActionEvent event) {
        FileChooser fc = new FileChooser();

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

        // set the text area the content of the file.
        try {
            inputText.textProperty().setValue(Utils.fileToString(selectedFile));
            System.out.println(Utils.fileToString(selectedFile));
        } catch (FileNotFoundException e) {
            Utils.showErrorMessage("Error: file not found.");
        }
    }

    @FXML
    private void onPendingJobsClicked(MouseEvent e) {
        if (e.getClickCount() == 2 && e.getButton().equals(MouseButton.PRIMARY)) {
        }
    }

    private void setBindings() {
        inputText.textProperty().bindBidirectional(customerModelView.inputTextProperty());
        inputText.textProperty().addListener(((observableValue, oldV, newV) -> {
            wordCount.textProperty().setValue(String.valueOf(newV.split(" ").length));
        }));

        wordCount.textProperty().bindBidirectional(customerModelView.wordCountProperty(), new NumberStringConverter());
        textPrice.textProperty().bindBidirectional(customerModelView.textPriceProperty(), new CurrencyStringConverter());
        textDeadline.textProperty().bindBidirectional(customerModelView.textDeadlineProperty());
        selectedItemPrice.textProperty()
                .bindBidirectional(customerModelView.selectedItemPriceProperty(), new NumberStringConverter());
        selectedItemDeadline.textProperty().bindBidirectional(customerModelView.selectedItemDeadlineProperty());
        filePath.textProperty().bindBidirectional(customerModelView.filePathProperty());
    }

    private void setTable() {
        itemName.setCellValueFactory(new PropertyValueFactory<Job, Integer>("id"));
        itemStatus.setCellValueFactory(jobStringCellDataFeatures -> {
            if (jobStringCellDataFeatures.getValue().getAssignedTo() == null) {
                return new SimpleStringProperty("Assigning");
            }
            if (jobStringCellDataFeatures.getValue().isTranslationCompleted()) {
                return new SimpleStringProperty("Done");
            }
            return new SimpleStringProperty("Translating");
        });
        itemAssignedTo.setCellValueFactory((jobUserCellDataFeatures -> {
            if (jobUserCellDataFeatures.getValue().getAssignedTo() == null) return new SimpleStringProperty("");
            return new SimpleStringProperty(jobUserCellDataFeatures.getValue().getAssignedTo().getUsername());
        }));

        usersPendingJobsListView.itemsProperty().setValue(customerModelView.getCustomersPendingJobsList());

        usersPendingJobsListView.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            if (newV == null) return;
            selectedItemPrice.setText(String.valueOf(newV.getPrice()));
            selectedItemDeadline
                    .setText(LocalDate.ofInstant(newV.getApproximatedDeadline().toInstant(), ZoneId.systemDefault())
                            .format(Utils.dateTimeFormatter));
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerModelView = CustomerModelView.getInstance();
        setBindings();
        setTable();
    }
}
