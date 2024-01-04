package lk.ijse.animal_clinic.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.animal_clinic.dto.customerDto;
import lk.ijse.animal_clinic.dto.tm.CustomerTm;
import lk.ijse.animal_clinic.model.CustomerModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class CustomerFormController {
    @FXML
    private TableView<CustomerTm> tblCustomer;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTel;



    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;


    private AnchorPane root;

    @FXML
    private TextField txtAddress;

    @FXML
    private AnchorPane subsubPane;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTel;
    public void initialize() {

        String css = getClass().getResource("/sheets/tableSheet.css").toExternalForm();
        tblCustomer.getStylesheets().add(css);
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.7), subsubPane);
        scaleTransition.setFromX(0);
        scaleTransition.setFromY(0);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.play();
        setCellValueFactory();
        loadAllCustomers();
        generateNextCustomerId();
        setAnimation(tblCustomer);
    }


    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
    }
    private void loadAllCustomers() {
        var model = new CustomerModel();

        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();

        try {
            List<customerDto> dtoList = model.getAllCustomers();

            for(customerDto dto : dtoList) {
                obList.add(
                        new CustomerTm(
                                dto.getId(),
                                dto.getName(),
                                dto.getTel(),
                                dto.getEmail(),
                                dto.getAddress()

                        )
                );
            }

            tblCustomer.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

        String id = txtId.getText();

        var model = new CustomerModel();
        try {
            customerDto dto = model.searchCustomer(id);

            if(dto != null) {
                fillFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    private void fillFields(customerDto dto) {
        txtId.setText(dto.getId());
        txtName.setText(dto.getName());
        txtTel.setText(dto.getTel());
        txtEmail.setText(dto.getEmail());
        txtAddress.setText(dto.getAddress());


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
        String id = txtId.getText();

        var customerModel = new CustomerModel();
        try {
            boolean isDeleted = customerModel.deleteCustomer(id);

            if(isDeleted) {
                tblCustomer.refresh();

                new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!").show();
                loadAllCustomers();
                setCellValueFactory();
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
        boolean isValidate = validatedCustomer();
        if (isValidate) {
            String id = txtId.getText();
            String name = txtName.getText();
            String tel = txtTel.getText();
            String email = txtEmail.getText();
            String address = txtAddress.getText();

            var dto = new customerDto(id, name, tel, email, address);

            var model = new CustomerModel();

            try {
                boolean isSaved = model.saveCustomer(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
                    loadAllCustomers();
                    setCellValueFactory();
                    clearFields();
                    generateNextCustomerId();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

        }
    }
    private boolean validatedCustomer() {
        int num=0;
        String id = txtId.getText();
        boolean isCustomerIdValidated = Pattern.matches("[C][0-9]{3,}", id);
        if (!isCustomerIdValidated) {
            vibrateTextField(txtId);
//           // new Alert(Alert.AlertType.ERROR, "INVALID Id").show();
            num =1;
        }

        String name = txtName.getText();
        boolean isCustomerNameValidated = Pattern.matches("[A-Za-z]{3,}", name);
        if (!isCustomerNameValidated) {
            vibrateTextField(txtName);
            //  new Alert(Alert.AlertType.ERROR, "INVALID Name").show();
            num =1;
        }


        String tel = txtTel.getText();
        boolean isCustomerTelValidated = Pattern.matches("[0-9]{10}", tel);
        if (!isCustomerTelValidated) {
            vibrateTextField(txtTel);
            //  new Alert(Alert.AlertType.ERROR, "INVALID Tel").show();
            num =1;
        }

        String email = txtEmail.getText();
        boolean isCustomerEmailValidated = Pattern.matches("[a-z].*(com|lk)", email);
        if (!isCustomerEmailValidated) {
            vibrateTextField(txtEmail);
            //   new Alert(Alert.AlertType.ERROR, "INVALID Email").show();
            num =1;
        }
        String address = txtAddress.getText();
        boolean isCustomerAddressValidated = Pattern.matches("[A-Za-z0-9/.\\s]{3,}", address);
        if (!isCustomerAddressValidated) {
            vibrateTextField(txtAddress);
            //    new Alert(Alert.AlertType.ERROR, "INVALID Address").show();
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

        String id = txtId.getText();
        String name = txtName.getText();
        String tel = txtTel.getText();
        String email = txtEmail.getText();
        String address = txtAddress.getText();


        var dto = new customerDto(id,name,tel,email,address);

        var model = new CustomerModel();

        try {
            boolean isUpdated = model.updateCustomer(dto);
            System.out.println(isUpdated);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, " customer updated!").show();
                clearFields();
                loadAllCustomers();
                setCellValueFactory();
            }

        }
        catch(SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }


    }
    void clearFields() {
        txtEmail.setText("");
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtTel.setText("");
    }

    private void generateNextCustomerId() {
        try {
            CustomerModel customerModel = new CustomerModel();
            String customerID = customerModel.generateNextCustomerID();
            txtId.setText(customerID);
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