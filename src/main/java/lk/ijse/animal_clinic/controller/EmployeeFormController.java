package lk.ijse.animal_clinic.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import lk.ijse.animal_clinic.dto.EmployeeDto;
import lk.ijse.animal_clinic.dto.customerDto;
import lk.ijse.animal_clinic.dto.tm.CustomerTm;
import lk.ijse.animal_clinic.dto.tm.EmployeeTm;
import lk.ijse.animal_clinic.model.CustomerModel;
import lk.ijse.animal_clinic.model.DoctorModel;
import lk.ijse.animal_clinic.model.EmployeeModel;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class EmployeeFormController {
    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colEmpAddress;

    @FXML
    private TableColumn<?, ?> colEmpEmail;

    @FXML
    private TableColumn<?, ?> colEmpId;

    @FXML
    private TableColumn<?, ?> colEmpName;

    @FXML
    private TableColumn<?, ?> colEmpTel;

    @FXML
    private TableView<EmployeeTm> tblEmployee;
    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmpEmail;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private TextField txtEmpName;


    @FXML
    private TextField txtTel;


    public void initialize() {
        setCellValueFactory();
        loadAllEmployee();
        generateNextEmployeeID();
        setAnimation(tblEmployee);


    }
    private void setCellValueFactory() {
        colEmpId .setCellValueFactory(new PropertyValueFactory<>("emp_id"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmpTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colEmpEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEmpAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
    }

    private void loadAllEmployee() {
        var model = new EmployeeModel();

        ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();

        try {
            List<EmployeeDto> dtoList = model.getAllEmployee();

            for(EmployeeDto dto : dtoList) {
                obList.add(
                        new EmployeeTm(
                                dto.getEmp_id(),
                                dto.getName(),
                                dto.getTel(),
                                dto.getEmail(),
                                dto.getAddress()

                        )
                );
            }

            tblEmployee.setItems(obList);
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

        String emp_id = txtEmployeeId.getText();

        var model = new EmployeeModel();
        try {
            boolean isDeleted = model.deleteEmployee(emp_id);
            tblEmployee.refresh();
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "delectd").show();
                setCellValueFactory();
                loadAllEmployee();
                generateNextEmployeeID();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();

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
        boolean isValidate = validatedEmployee();
        if (isValidate) {
            String emp_id = txtEmployeeId.getText();
            String name = txtEmpName.getText();
            String tel = txtTel.getText();
            String email = txtEmpEmail.getText();
            String address = txtAddress.getText();

            var dto = new EmployeeDto(emp_id, name, tel, email, address);

            var model = new EmployeeModel();

            try {
                boolean isSaved = model.saveEmployee(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "employee saved!").show();
                    clearFields();
                    setCellValueFactory();
                    loadAllEmployee();
                    generateNextEmployeeID();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }


        }
    }

    private boolean validatedEmployee() {
        int num =0;
        String id = txtEmployeeId.getText();
        boolean isEmployeeIdValidated = Pattern.matches("[E][0-9]{3,}", id);
        if (!isEmployeeIdValidated) {
            vibrateTextField(txtEmployeeId);
            // new Alert(Alert.AlertType.ERROR, "INVALID Id").show();
            num=1;
        }
        String name = txtEmpName.getText();
        boolean isEmployeeNameValidated = Pattern.matches("[A-Za-z]{3,}", name);
        if (!isEmployeeNameValidated) {
            vibrateTextField(txtEmpName);
            // new Alert(Alert.AlertType.ERROR, "INVALID Name").show();
            num =1;
        }

        String tel = txtTel.getText();
        boolean isEmployeeTelValidated = Pattern.matches("[0-9]{10}", tel);
        if (!isEmployeeTelValidated) {
            vibrateTextField(txtTel);
            //  new Alert(Alert.AlertType.ERROR, "INVALID Tel").show();
            num =1;
        }
        String email = txtEmpEmail.getText();
        boolean isEmployeeEmailValidated = Pattern.matches("[a-z].*(com|lk)", email);
        if (!isEmployeeEmailValidated) {
            vibrateTextField(txtEmpEmail);
            //  new Alert(Alert.AlertType.ERROR, "INVALID Email").show();
            num =1;
        }
        String address = txtAddress.getText();
        boolean isEmployeeAddressValidated = Pattern.matches("[A-Za-z0-9/.\\s]{3,}", address);
        if (!isEmployeeAddressValidated) {
            vibrateTextField(txtAddress);
            //  new Alert(Alert.AlertType.ERROR, "INVALID Address").show();
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
        String emp_id = txtEmployeeId.getText();
        String name = txtEmpName.getText();
        String tel = txtTel.getText();
        String email = txtEmpEmail.getText();
        String address = txtAddress.getText();

        var dto = new EmployeeDto(emp_id , name, tel , email,address);

        var model = new EmployeeModel();

        try {
            boolean isUpdated = model.updateEmployee(dto);
            System.out.println(isUpdated);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, " employee updated!").show();
                clearFields();
                setCellValueFactory();
                loadAllEmployee();
            }

        }
        catch(SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }


    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String emp_id = txtEmployeeId.getText();

        var model = new EmployeeModel();
        try {
            EmployeeDto dto = model.searchEmployee(emp_id);

            if(dto != null) {
                fillFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Employee not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }
    private void fillFields(EmployeeDto dto) {
        txtEmployeeId.setText(dto.getEmp_id());
        txtEmpName.setText(dto.getName());
        txtTel.setText(dto.getTel());
        txtEmpEmail.setText(dto.getEmail());
        txtAddress.setText(dto.getAddress());


    }


    void clearFields() {
        txtEmpName.setText("");
        txtEmpEmail.setText("");
        txtEmployeeId.setText("");
        txtAddress.setText("");
        txtTel.setText("");
    }

    private void generateNextEmployeeID() {
        try {
            EmployeeModel employeeModel = new EmployeeModel();
            String orderId = employeeModel.generateNextEmployeeId();
            txtEmployeeId.setText(orderId);
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
