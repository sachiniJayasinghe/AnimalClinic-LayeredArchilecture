package lk.ijse.animal_clinic.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import lk.ijse.animal_clinic.dto.AppointmentDto;
import lk.ijse.animal_clinic.dto.EmployeeDto;
import lk.ijse.animal_clinic.dto.SalaryDto;
import lk.ijse.animal_clinic.dto.customerDto;
import lk.ijse.animal_clinic.dto.tm.AppointmentTm;
import lk.ijse.animal_clinic.dto.tm.SalaryTm;
import lk.ijse.animal_clinic.model.AppointmentModel;
import lk.ijse.animal_clinic.model.CustomerModel;
import lk.ijse.animal_clinic.model.EmployeeModel;
import lk.ijse.animal_clinic.model.SalaryModel;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.regex.Pattern;

public class SalaryFormController {
    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;
    @FXML
    private TableColumn<?, ?> colSalaryAmount;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colEmpId;

    @FXML
    private TableColumn<?, ?> colSalaryId;

    @FXML
    private TableColumn<?, ?> colSalaryMonths;

    @FXML
    private TableView<SalaryTm> tblSalary;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtEmpId;

    @FXML
    private TextField txtMonths;

    @FXML
    private TextField txtSalaryId;

    @FXML
    private TextField txtSalaryAmount;

    @FXML
    private JFXComboBox<String> cmbEmpId;
    public void initialize() {
        setCellValueFactory();
        loadAllSalary();
        loadEmpIds();
        setAnimation(tblSalary);


    }
    private void setCellValueFactory() {
        colSalaryId.setCellValueFactory(new PropertyValueFactory<>("salary_id"));
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("emp_id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colSalaryMonths.setCellValueFactory(new PropertyValueFactory<>("salary_month"));
        colSalaryAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

    }
    private void loadAllSalary() {
        var model = new SalaryModel();

        ObservableList<SalaryTm> obList = FXCollections.observableArrayList();

        try {
            List<SalaryDto> dtoList = model.getAllSalary();

            for(SalaryDto dto : dtoList) {
                obList.add(
                        new SalaryTm(
                                dto.getSalary_id(),
                                dto.getEmp_id(),
                                dto.getDate(),
                                dto.getSalary_month(),
                                dto.getAmount()

                        )
                );
            }

            tblSalary.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadEmpIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<EmployeeDto> cusList = EmployeeModel.getAllEmployee();

            for (EmployeeDto dto : cusList) {
                obList.add(dto.getEmp_id());
            }
            cmbEmpId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String salary_id = txtSalaryId.getText();


        var SalaryModel  = new SalaryModel();
        try {
            boolean isDeleted = SalaryModel.deleteSalary(salary_id);

            if(isDeleted) {
                tblSalary.refresh();

                new Alert(Alert.AlertType.CONFIRMATION, "salary deleted!").show();
                setCellValueFactory();
                loadAllSalary();
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
        boolean isValidate = validatedSalary();
        if(isValidate) {
            String salary_id = txtSalaryId.getText();
            String emp_id = cmbEmpId.getValue();
            Date date = java.sql.Date.valueOf(txtDate.getValue());
            String salary_month = txtMonths.getText();
            double amount = Double.parseDouble(txtSalaryAmount.getText());
            var dto = new SalaryDto(salary_id, emp_id, date, salary_month, amount);

            var model = new SalaryModel();

            try {
                boolean isSaved = model.saveSalary(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, " saved!").show();
                    clearFields();
                    setCellValueFactory();
                    loadAllSalary();

                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

            }

        }
    }

    private boolean validatedSalary() {
        int num =0;
        String salary_id = txtSalaryId.getText();
        boolean isSalaryIdValidated = Pattern.matches("[S][0-9]{3,}",salary_id );
        if (!isSalaryIdValidated) {
            vibrateTextField(txtSalaryId);
           // new Alert(Alert.AlertType.ERROR, "INVALID  salary id").show();
            num =1;
        }
        String salary_month = txtMonths.getText();
        boolean isSalaryMonthValidated = Pattern.matches("[A-Za-z]{3,}", salary_month );
        if (!isSalaryMonthValidated) {
            vibrateTextField(txtMonths);
          //  new Alert(Alert.AlertType.ERROR, "INVALID  salary Month").show();
            num =1;
        }
        double amount = Double.parseDouble(txtSalaryAmount.getText());
        String amountString = String.format("%.2f", amount);
        boolean isSalaryAmountValidated = Pattern.matches("^[1-9]\\d{0,6}\\.\\d{2}$", amountString);;
        if (!isSalaryAmountValidated) {
            vibrateTextField(txtSalaryAmount);
          //  new Alert(Alert.AlertType.ERROR, "INVALID Amount").show();
            num =1;
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
        String salary_id = txtSalaryId.getText();
        String emp_id = cmbEmpId.getValue();
        Date date = java.sql.Date.valueOf(txtDate.getValue());
        String salary_month = txtMonths.getText();
        double amount = Double.parseDouble(txtSalaryAmount.getText());

        var dto = new SalaryDto(salary_id, emp_id,  date, salary_month,amount);

        var model = new SalaryModel();


        try {
            boolean isUpdated = model.updateSalary(dto);
            System.out.println(isUpdated);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, " Appointment updated!").show();
                clearFields();
                setCellValueFactory();
                loadAllSalary();
            }

        }
        catch(
SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }


    }
    @FXML
    void cmbEmpIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

        String salary_id = txtSalaryId.getText();

        var model = new SalaryModel();
        try {
            SalaryDto dto = model.searchSalary(salary_id);

            if(dto != null) {
                fillFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "SALARY not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }
    private void fillFields(SalaryDto dto) {
        txtSalaryId.setText(dto.getSalary_id());
        cmbEmpId.setValue(dto.getEmp_id());
        txtDate.setValue(dto.getDate().toLocalDate());
        txtMonths.setText(dto.getSalary_month());
        txtSalaryAmount.setText(String.valueOf(dto.getAmount()));


    }
    void clearFields() {
        txtSalaryId.setText("");
        cmbEmpId.setValue("");
        txtMonths.setText("");
        txtSalaryAmount.setText("");
        txtDate.setPromptText(" ");
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
