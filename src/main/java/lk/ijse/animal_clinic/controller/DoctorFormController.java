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
import lk.ijse.animal_clinic.dto.DoctorDto;
import lk.ijse.animal_clinic.dto.tm.DoctorTm;
import lk.ijse.animal_clinic.model.DoctorModel;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.regex.Pattern;

public class DoctorFormController {
    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTel;

    @FXML
    private TextField txtComeInTime;

    @FXML
    private TextField txtOutTime;
    @FXML
    private TableColumn<?, ?> colDoctorAddress;

    @FXML
    private TableColumn<?, ?> colDoctorEmail;

    @FXML
    private TableColumn<?, ?> colDoctorId;

    @FXML
    private TableColumn<?, ?> colDoctorName;

    @FXML
    private TableColumn<?, ?> colDoctorTel;

    @FXML
    private TableView<DoctorTm> tblDoctor;

    @FXML
    private TableColumn<?, ?> colComeInTime;

    @FXML
    private TableColumn<?, ?> colOutTime;

    public void initialize() {
        setCellValueFactory();
        loadAllDoctor();
        generateNextOrderId();
        setAnimation(tblDoctor);

    }

    private void setCellValueFactory() {
        colDoctorId   .setCellValueFactory(new PropertyValueFactory<>("id"));
        colDoctorName  .setCellValueFactory(new PropertyValueFactory<>("name"));
        colDoctorTel .setCellValueFactory(new PropertyValueFactory<>("tel"));
        colDoctorEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colDoctorAddress .setCellValueFactory(new PropertyValueFactory<>("address"));
        colComeInTime.setCellValueFactory(new PropertyValueFactory<>("comeInTime"));
        colOutTime.setCellValueFactory(new PropertyValueFactory<>("outTime"));
    }
    private void loadAllDoctor() {
        var model = new DoctorModel();

        ObservableList<DoctorTm> obList = FXCollections.observableArrayList();

        try {
            List<DoctorDto> dtoList = model.getAllDoctor();

            for(DoctorDto dto : dtoList) {
                obList.add(
                        new DoctorTm(
                                dto.getId(),
                                dto.getName(),
                                dto.getTel(),
                                dto.getEmail(),
                                dto.getAddress(),
                                dto.getComeInTime(),
                                dto.getOutTime()
                        )
                );
            }

            tblDoctor.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }    @FXML
    void btnBackOnAction(ActionEvent event) {

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String doctor_id = txtId.getText();

        var model = new DoctorModel();
        try {
            boolean isDeleted = model.doctorDelete(doctor_id);
            tblDoctor.refresh();

            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "delectd").show();
                setCellValueFactory();
                loadAllDoctor();
                generateNextOrderId();

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

        boolean isDoctorValidated = validateDoctor();
        if (isDoctorValidated) {
            String doctor_id = txtId.getText();
            String doctor_name = txtName.getText();
            String tel = txtTel.getText();
            String e_mail = txtEmail.getText();
            String address = txtAddress.getText();
            Time comeInTime = java.sql.Time.valueOf(txtComeInTime.getText());
            Time outTime = java.sql.Time.valueOf(txtOutTime.getText());

            var dto = new DoctorDto(doctor_id, doctor_name, tel, e_mail, address, comeInTime, outTime);
            var model = new DoctorModel();

            try {
                boolean isSaved = model.saveDoctor(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "DOCTOR SAVED").show();
                    clearFields();
                    setCellValueFactory();
                    loadAllDoctor();
                    generateNextOrderId();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

        }
    }
    private boolean validateDoctor() {
        int num =0;
        String doctor_id = txtId.getText();
        boolean isDoctorIdValidated = Pattern.matches("[D][0-9]{3,}", doctor_id);
        if (!isDoctorIdValidated) {
            vibrateTextField(txtId);
            // new Alert(Alert.AlertType.ERROR, "INVALID id").show();
            num=1;
        }
        String doctor_name = txtName.getText();
        boolean isDoctorNameValidated = Pattern.matches("[A-Za-z]{3,}", doctor_name);
        if (!isDoctorNameValidated) {
            vibrateTextField(txtName);
            //   new Alert(Alert.AlertType.ERROR, "INVALID name").show();
            num=1;
        }
        String tel = txtTel.getText();
        boolean isDoctorTelValidated = Pattern.matches("[0-9]{10}", tel);
        if (!isDoctorTelValidated) {
            vibrateTextField(txtTel);
            //  new Alert(Alert.AlertType.ERROR, "INVALID tel").show();
            num =1;
        }
        String e_mail = txtEmail.getText();
        boolean isDoctorEmailValidated = Pattern.matches("[a-z].*(com|lk)", e_mail);
        if (!isDoctorEmailValidated) {
            vibrateTextField(txtEmail);
            // new Alert(Alert.AlertType.ERROR, "INVALID email").show();
            num =1;
        }
        String address = txtAddress.getText();
        boolean isDoctorAddressValidated = Pattern.matches("[A-Za-z0-9/.\\s]{3,}", address);
        if (!isDoctorAddressValidated) {
            vibrateTextField(txtAddress);
            //new Alert(Alert.AlertType.ERROR, "INVALID address").show();
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


        String doctor_id = txtId.getText();
        String doctor_name = txtName.getText();
        String tel = txtTel.getText();
        String e_mail = txtEmail.getText();
        String address = txtAddress.getText();
        Time comeInTime = java.sql.Time.valueOf(txtComeInTime.getText());
        Time outTime = java.sql.Time.valueOf(txtOutTime.getText());

        var dto = new DoctorDto(doctor_id ,doctor_name, tel,e_mail,address,comeInTime,outTime);


        var model = new DoctorModel();

        try {
            boolean isUpdated = model.updateDoctor(dto);
            System.out.println(isUpdated);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, " Doctor updated!").show();
                clearFields();
                setCellValueFactory();
                loadAllDoctor();
            }

        }
        catch(SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }



    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String doctor_id = txtId.getText();

        var model = new DoctorModel();
        try{
            DoctorDto dto = model.searchDoctor(doctor_id);
            if(dto!=null){
                fillFields(dto);
            }else{
                new Alert(Alert.AlertType.INFORMATION,"DOCTOR NOT FOUND").show();

            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();

        }

    }
    private  void fillFields(DoctorDto dto){
        txtId.setText(dto.getId());
        txtName.setText(dto.getName());
        txtTel.setText(dto.getTel());
        txtEmail.setText(dto.getEmail());
        txtAddress.setText(dto.getAddress());
        txtComeInTime.setText(String.valueOf(dto.getComeInTime()));
        txtOutTime.setText(String.valueOf(dto.getOutTime()));
    }

    void clearFields(){
        txtId.setText("");
        txtName.setText("");
        txtTel.setText("");
        txtEmail.setText("");
        txtAddress.setText("");
        txtComeInTime.setText("");
        txtOutTime.setText("");
    }

    private void generateNextOrderId() {
        try {
            DoctorModel doctorModel = new DoctorModel();
            String doctorId = doctorModel.generateNextDoctorId();
            txtId.setText(doctorId);
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
