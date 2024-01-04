package lk.ijse.animal_clinic.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.animal_clinic.dto.AppointmentDto;
import lk.ijse.animal_clinic.dto.PetDto;
import lk.ijse.animal_clinic.dto.customerDto;
import lk.ijse.animal_clinic.dto.tm.CustomerTm;
import lk.ijse.animal_clinic.dto.tm.PetTm;
import lk.ijse.animal_clinic.model.AppointmentModel;
import lk.ijse.animal_clinic.model.CustomerModel;
import lk.ijse.animal_clinic.model.PetModel;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class PetFormController {
    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtType;
    @FXML
    private TextField txtCusid;
    @FXML
    private TableView<PetTm> tblPet;


    @FXML
    private TableColumn<?, ?> colCusId;

    @FXML
    private TableColumn<?, ?> colPetAge;

    @FXML
    private TableColumn<?, ?> colPetId;

    @FXML
    private TableColumn<?, ?> colPetName;

    @FXML
    private TableColumn<?, ?> colPetType;

    @FXML
    private AnchorPane subPane;

    @FXML
    private JFXComboBox<String> cmbCusId;




    @FXML
    void btnBackOnAction(ActionEvent event) {

    }
    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }
    public void initialize() {
        TranslateTransition slideIn = new TranslateTransition(Duration.seconds(0.7), subPane);
        slideIn.setFromX(600);
        slideIn.setToX(0);
        slideIn.play();
        setCellValueFactory();
        loadAllPet();
        loadCustomerIds();
        generateNextPetId();
        setAnimation(tblPet);


    }
    private void setCellValueFactory() {
        colPetId.setCellValueFactory(new PropertyValueFactory<>("pet_id"));
        colPetName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPetAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colPetType.setCellValueFactory(new PropertyValueFactory<>("pet_type"));
        colCusId .setCellValueFactory(new PropertyValueFactory<>("cus_id"));
    }
    private void loadAllPet() {
        var model = new PetModel();

        ObservableList<PetTm> obList = FXCollections.observableArrayList();

        try {
            List<PetDto> dtoList = model.getAllPet();

            for(PetDto dto : dtoList) {
                obList.add(
                        new PetTm(
                                dto.getPet_id(),
                                dto.getName(),
                                dto.getPet_type(),
                                dto.getAge(),
                                dto.getCus_id()

                        )
                );
            }
            tblPet.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadCustomerIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<customerDto> cusList = CustomerModel.getAllCustomers();

            for (customerDto dto : cusList) {
                obList.add(dto.getId());
            }
            cmbCusId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }










    @FXML
    void cmbCusIdOnAction(ActionEvent event) {


    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        String pet_id = txtId.getText();

        var petModel = new PetModel();

        try{
            boolean isDeleted = petModel.deletePet(pet_id);
            tblPet.refresh();
            if(isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"PET deleted").show();
                setCellValueFactory();
                loadAllPet();
                generateNextPetId();
            }
        }catch (SQLException e){
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
        boolean isPetValidated = validatePet();
        if(isPetValidated) {
            String pet_id = txtId.getText();
            String name = txtName.getText();
            String pet_type = txtType.getText();
            String age = txtAge.getText();
            String cus_id = cmbCusId.getValue();

            var dto = new PetDto(pet_id, name, pet_type, age, cus_id);

            var model = new PetModel();
            try {
                boolean isSaved = model.savePet(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
                    clearFields();
                    setCellValueFactory();
                    loadAllPet();
                    generateNextPetId();

                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    private boolean validatePet() {


        int num = 0;

        String pet_id = txtId.getText();
        boolean isPetIdValidated = Pattern.matches("[P][0-9]{3,}", pet_id);
        if (!isPetIdValidated) {
            vibrateTextField(txtId);
            //     new Alert(Alert.AlertType.ERROR, "INVALID pet id").show();
            num=1;

        }

        String name = txtName.getText();
        boolean isPetNameValidated = Pattern.matches("[A-Za-z]{3,}", name);
        if (!isPetNameValidated) {
            vibrateTextField(txtName);
            //  new Alert(Alert.AlertType.ERROR, "INVALID name").show();
            num=1;

        }

        String pet_type = txtType.getText();
        boolean isPetTypeValidated = Pattern.matches("[A-Za-z]{3,}", pet_type);
        if (!isPetTypeValidated) {
            vibrateTextField(txtType);
            // new Alert(Alert.AlertType.ERROR, "INVALID type").show();

            num=1;
        }
        String age = txtAge.getText();
        boolean isPetAgeValidated = Pattern.matches("\\d+\\s(years|months)", age);
        if (!isPetAgeValidated) {
            vibrateTextField(txtAge);
            //  new Alert(Alert.AlertType.ERROR, "INVALID age").show();
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
        String pet_id = txtId.getText();
        String name = txtName.getText();
        String pet_type = txtType.getText();
        String age = txtAge.getText();
        String cus_id = cmbCusId.getValue();


        var dto = new PetDto(pet_id,name,pet_type,age,cus_id);
        var model = new PetModel();

        try{
            boolean isUpdated = model.updatePet(dto);
            System.out.println(isUpdated);

            if( isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"PET updated!").show();
                setCellValueFactory();
                loadAllPet();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String pet_id = txtId.getText();

        var model =  new PetModel();
        try {
            PetDto dto = PetModel.searchPet(pet_id);
            if(dto!= null){
                fillFields(dto);
            }else {
                new Alert(Alert.AlertType.INFORMATION,"PET not Found").show();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();

        }
    }
    private void fillFields(PetDto dto){
        txtId.setText(dto.getPet_id());
        txtName.setText(dto.getName());
        txtAge.setText(dto.getAge());
        txtType.setText(dto.getPet_type());
        cmbCusId.setValue(dto.getCus_id());
    }
    void clearFields() {
        txtType.setText("");
        txtId.setText("");
        txtName.setText("");
        txtAge.setText("");
        cmbCusId.setValue("");
    }

    private void generateNextPetId() {
        try {
            PetModel petModel = new PetModel();
            String orderId = petModel.generateNextPetId();
            txtId.setText(orderId);
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
