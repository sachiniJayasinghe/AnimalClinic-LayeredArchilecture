package lk.ijse.animal_clinic.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import lk.ijse.animal_clinic.dto.AppointmentDto;
import lk.ijse.animal_clinic.dto.TreatementDto;
import lk.ijse.animal_clinic.dto.tm.AppointmentTm;
import lk.ijse.animal_clinic.dto.tm.vaccinationsTm;
import lk.ijse.animal_clinic.dto.vaccinationsDto;
import lk.ijse.animal_clinic.model.AppointmentModel;
import lk.ijse.animal_clinic.model.TreatementModel;
import lk.ijse.animal_clinic.model.VaccinationModel;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.regex.Pattern;

public class VaccinationFormController {

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colNextDueDate;

    @FXML
    private TableColumn<?, ?> colVaccinationId;

    @FXML
    private TableColumn<?, ?> colVaccinationName;

    @FXML
    private TableView<vaccinationsTm> tblVaccination;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtVaccinationName;

    @FXML
    private TextField txtVaccinationId;

    public void initialize() {
        setCellValueFactory();
        loadAllVaccination();
        setAnimation(tblVaccination);

    }
    private void setCellValueFactory() {
        colVaccinationId.setCellValueFactory(new PropertyValueFactory<>("vaccination_id"));
        colVaccinationName.setCellValueFactory(new PropertyValueFactory<>("VaccinationName"));
        colNextDueDate.setCellValueFactory(new PropertyValueFactory<>("next_dueDate"));
    }

    private void loadAllVaccination() {
        var model = new VaccinationModel();

        ObservableList<vaccinationsTm> obList = FXCollections.observableArrayList();

        try {
            List<vaccinationsDto> dtoList = model.getAllVaccinatoin();

            for(vaccinationsDto dto : dtoList) {
                obList.add(
                        new vaccinationsTm(
                                dto.getVaccination_id(),
                                dto.getVaccinationName(),
                                dto.getNext_dueDate()

                        )
                );
            }

            tblVaccination.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnBackOnAction(ActionEvent event) {

    }
    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }
    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        String vaccination_id = txtVaccinationId.getText();
        var VaccinationModel = new VaccinationModel();

        try {
            boolean isDeleted = VaccinationModel.VaccinationModel(vaccination_id);

            if(isDeleted) {
                tblVaccination.refresh();

                new Alert(Alert.AlertType.CONFIRMATION, " deleted!").show();
                setCellValueFactory();
                loadAllVaccination();

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }
    private void vibrateTextField(TextField textField) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(0), new KeyValue(textField.translateXProperty(), 0)),
                new KeyFrame(Duration.millis(50), new KeyValue(textField.translateXProperty(), -6)),
                new KeyFrame(Duration.millis(100), new KeyValue(textField.translateXProperty(), 6)),
                new KeyFrame(Duration.millis(150), new KeyValue(textField.translateXProperty(), -6)),
                new KeyFrame(Duration.millis(200), new KeyValue(textField.translateXProperty(), 6)),
                new KeyFrame(Duration.millis(250), new KeyValue(textField.translateXProperty(), -6)),
                new KeyFrame(Duration.millis(300), new KeyValue(textField.translateXProperty(), 6)),
                new KeyFrame(Duration.millis(350), new KeyValue(textField.translateXProperty(), -6)),
                new KeyFrame(Duration.millis(400), new KeyValue(textField.translateXProperty(), 0))

        );

        textField.setStyle("-fx-border-color: red;");
        timeline.play();

        Timeline timeline1 = new Timeline(
                new KeyFrame(Duration.seconds(3), new KeyValue(textField.styleProperty(), "-fx-border-color: #bde0fe;"))
        );

        timeline1.play();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        boolean isValidate = validatedVaccination();
        if(isValidate) {
            String vaccination_id = txtVaccinationId.getText();
            String vaccine_name = txtVaccinationName.getText();
            Date Date = java.sql.Date.valueOf(txtDate.getValue());

            var dto = new vaccinationsDto(vaccination_id, vaccine_name, Date);

            var model = new VaccinationModel();

            try {
                boolean isSaved = model.saveVaccination(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, " saved!").show();
                    clearFields();
                    setCellValueFactory();
                    loadAllVaccination();

                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

            }
        }

    }

    private boolean validatedVaccination() {
        int num =0;
        String vaccination_id = txtVaccinationId.getText();
        boolean isVaccinationIdValidated = Pattern.matches("[V][0-9]{3,}",vaccination_id );
        if (!isVaccinationIdValidated) {
            vibrateTextField(txtVaccinationId);
           // new Alert(Alert.AlertType.ERROR, "INVALID  id").show();
            num=1;
        }
        String vaccine_name = txtVaccinationName.getText();
        boolean isVaccineNameValidated = Pattern.matches("[A-Za-z]{3,}",vaccine_name);
        if (!isVaccineNameValidated) {
            vibrateTextField(txtVaccinationName);
          //  new Alert(Alert.AlertType.ERROR, "INVALID name").show();
            num=1;
        }
        if(num==1){
            num = 0;
            return false;
        }else {
            num = 0;
            return true;
        }

    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) {


        String vaccination_id = txtVaccinationId.getText();
        String vaccine_name = txtVaccinationName.getText();
        Date Date = java.sql.Date.valueOf(txtDate.getValue());

        var dto = new vaccinationsDto(vaccination_id,vaccine_name,Date);
        var model = new VaccinationModel();
        try {
            boolean isUpdated = model.updateVaccination(dto);
            System.out.println(isUpdated);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "  updated!").show();
                clearFields();
                setCellValueFactory();
                loadAllVaccination();

            }

        }
        catch(SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }

    }
    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String vaccination_id = txtVaccinationId.getText();
        var model = new VaccinationModel();
        try {
            vaccinationsDto dto = model.searchVaccination(vaccination_id);

            if(dto != null) {
                fillFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, " not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }

    }
    private void fillFields(vaccinationsDto dto) {
        txtVaccinationId.setText(dto.getVaccination_id());
        txtDate.setValue(dto.getNext_dueDate().toLocalDate());
        txtVaccinationName.setText(dto.getVaccinationName());

    }
    void clearFields() {
        txtVaccinationId.setText("");
        txtVaccinationName .setText("");
    }
    private void setAnimation(TableView tbl){
        String css = getClass().getResource("/sheets/tableSheet.css").toExternalForm();
        tbl.getStylesheets().add(css);
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(3));
        pauseTransition.setOnFinished(event -> {
            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), tbl);
            translateTransition.setFromY(-50);
            translateTransition.setToY(0);
            translateTransition.play();

            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), tbl);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();
        });

        pauseTransition.play();
    }
}
