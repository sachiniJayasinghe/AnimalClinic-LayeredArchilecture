package lk.ijse.animal_clinic.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.animal_clinic.dto.AppointmentDto;
import lk.ijse.animal_clinic.dto.TreatementDto;
import lk.ijse.animal_clinic.dto.tm.TreatementTm;
import lk.ijse.animal_clinic.dto.tm.vaccinationsTm;
import lk.ijse.animal_clinic.dto.vaccinationsDto;
import lk.ijse.animal_clinic.model.AppointmentModel;
import lk.ijse.animal_clinic.model.TreatementModel;
import lk.ijse.animal_clinic.model.VaccinationModel;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.regex.Pattern;

public class ThreatmentFormController {

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colTreatementCost;

    @FXML
    private TableColumn<?, ?> colTreatementId;

    @FXML
    private TableColumn<?, ?> colTreatementType;

    @FXML
    private TableColumn<?, ?> colVaccinationId;

    @FXML
    private TableView<TreatementTm> tblTreatement;

    @FXML
    private TextField txtCost;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtTreatementId;
    @FXML
    private AnchorPane root;


    @FXML
    private TextField txtType;

    @FXML
    private TextField txtVaccinationId;
    public void initialize() {
        setCellValueFactory();
        loadAllTreatement();
        generateNextThreatmentID();
        setAnimation(tblTreatement);


    }



    private void setCellValueFactory() {
        colTreatementId.setCellValueFactory(new PropertyValueFactory<>("treatement_id"));
        colVaccinationId.setCellValueFactory(new PropertyValueFactory<>("vaccination_id"));
        colTreatementType .setCellValueFactory(new PropertyValueFactory<>("type"));
        colTreatementCost .setCellValueFactory(new PropertyValueFactory<>("cost"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }
    private void loadAllTreatement() {
        var model = new TreatementModel();

        ObservableList<TreatementTm> obList = FXCollections.observableArrayList();

        try {
            List<TreatementDto> dtoList = model.getAllTreatement();

            for(TreatementDto dto : dtoList) {
                obList.add(
                        new TreatementTm(
                                dto.getTreatement_id(),
                                dto.getVaccination_id(),
                                dto.getType(),
                                dto.getCost(),
                                dto.getDate()

                        )

                );
            }

            tblTreatement.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashBoard_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard");
        stage.centerOnScreen();
    }




    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();


    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String treatement_id = txtTreatementId.getText();
        var TreatementModel = new TreatementModel();




        try {
            boolean isDeleted = TreatementModel.TreatementModel(treatement_id);

            if(isDeleted) {
                tblTreatement.refresh();

                new Alert(Alert.AlertType.CONFIRMATION, " deleted!").show();
                setCellValueFactory();
                loadAllTreatement();
                generateNextThreatmentID();

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
        boolean isValidate = validatedTreatement();
        if(isValidate) {
            String treatement_id = txtTreatementId.getText();
            String vaccination_id = txtVaccinationId.getText();
            String type = txtType.getText();
            double cost = Double.parseDouble(txtCost.getText());
            Date Date = java.sql.Date.valueOf(txtDate.getValue());


            var dto = new TreatementDto(treatement_id, vaccination_id, type, cost, Date);

            var model = new TreatementModel();

            try {
                boolean isSaved = model.saveTreatement(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, " saved!").show();
                    clearFields();
                    setCellValueFactory();
                    loadAllTreatement();
                    generateNextThreatmentID();

                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

            }

        }

    }

    private boolean validatedTreatement() {
        int num =0;
        String treatement_id = txtTreatementId.getText();
        boolean isTreatementIdValidated = Pattern.matches("[T][0-9]{3,}",treatement_id );
        if (!isTreatementIdValidated) {
            vibrateTextField(txtTreatementId);
            //new Alert(Alert.AlertType.ERROR, "INVALID  id").show();
            num=1;
        }

        String vaccination_id = txtVaccinationId.getText();
        boolean isVaccinationIdValidated = Pattern.matches("[V][0-9]{3,}",vaccination_id );
        if (!isVaccinationIdValidated) {
            vibrateTextField(txtVaccinationId);
            // new Alert(Alert.AlertType.ERROR, "INVALID  id").show();
            num=1;
        }
        String type = txtType.getText();
        boolean isTreatementTypeValidated = Pattern.matches("[A-Za-z]{3,}",type);
        if (!isTreatementTypeValidated) {
            vibrateTextField(txtType);
            // new Alert(Alert.AlertType.ERROR, "INVALID type").show();
            num=1;
        }
        double cost = Double.parseDouble(txtCost.getText());
        String costString = String.format("%.2f", cost);
        boolean isTreatmentCostValidated = Pattern.matches("^[1-9]\\d{0,6}\\.\\d{2}$", costString);;
        if (!isTreatmentCostValidated) {
            vibrateTextField(txtCost);
            // new Alert(Alert.AlertType.ERROR, "INVALID Cost").show();
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
        String treatement_id = txtTreatementId.getText();
        String vaccination_id = txtVaccinationId.getText();
        String type = txtType.getText();
        double cost = Double.parseDouble(txtCost.getText());
        Date Date = java.sql.Date.valueOf(txtDate.getValue());


        var dto = new TreatementDto(treatement_id,vaccination_id,type,cost,Date);

        var model = new TreatementModel();
        try {
            boolean isUpdated = model.updateTreatement(dto);
            System.out.println(isUpdated);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "  updated!").show();
                clearFields();
                setCellValueFactory();
                loadAllTreatement();

            }

        }
        catch(SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }




    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String treatement_id = txtTreatementId.getText();
        var TreatementModel = new TreatementModel();


        try {
            TreatementDto dto =TreatementModel .searchTreatment(treatement_id);

            if(dto != null) {
                fillFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, " not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }


    }
    private void fillFields(TreatementDto dto) {
        txtTreatementId.setText(dto.getTreatement_id());
        txtVaccinationId.setText(dto.getVaccination_id());
        txtType.setText(dto.getType());
        txtCost.setText(String.valueOf(dto.getCost()));
        txtDate.setValue(dto.getDate().toLocalDate());

    }
    void clearFields() {
        txtVaccinationId.setText("");
        txtTreatementId.setText("");
        txtType.setText("");
        txtCost.setText("");
    }

    private void generateNextThreatmentID() {
        try {
            TreatementModel treatementModel = new TreatementModel();
            String orderId = treatementModel.generateNextThreatmentId();
            txtTreatementId.setText(orderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
