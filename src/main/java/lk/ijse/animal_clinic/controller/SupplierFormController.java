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
import javafx.util.Duration;
import lk.ijse.animal_clinic.dto.StockDto;
import lk.ijse.animal_clinic.dto.SupliyerDto;
import lk.ijse.animal_clinic.dto.customerDto;
import lk.ijse.animal_clinic.dto.tm.SupliyerTm;
import lk.ijse.animal_clinic.model.CustomerModel;
import lk.ijse.animal_clinic.model.StockModel;
import lk.ijse.animal_clinic.model.SupliyerModel;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class SupplierFormController {

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colStockId;

    @FXML
    private TableColumn<?, ?> colSupliyerAddress;

    @FXML
    private TableColumn<?, ?> colSupliyerEmail;

    @FXML
    private TableColumn<?, ?> colSupliyerId;

    @FXML
    private TableColumn<?, ?> colSupliyerName;

    @FXML
    private TableColumn<?, ?> colSupliyerTel;

    @FXML
    private TableView<SupliyerTm> tblSupliyer;

    @FXML
    private TextField txtAdress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtStockId;

    @FXML
    private TextField txtSupliyerId;

    @FXML
    private TextField txtTel;

    @FXML
    private JFXComboBox<String> cmbStockId;
    public void initialize() {
        setCellValueFactory();
        loadAllSupliyer();
        loadStockIds();
        setAnimation(tblSupliyer);


    }

    @FXML
    void btnBackOnAction(ActionEvent event) {

    }



    private void setCellValueFactory() {
        colSupliyerId .setCellValueFactory(new PropertyValueFactory<>("supliyer_id"));
        colStockId.setCellValueFactory(new PropertyValueFactory<>("stock_id"));
        colSupliyerName.setCellValueFactory(new PropertyValueFactory<>("supliyer_name"));
        colSupliyerTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colSupliyerEmail.setCellValueFactory(new PropertyValueFactory<>("e_mail"));
        colSupliyerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
    }
    private void loadAllSupliyer() {
        var model = new SupliyerModel();

        ObservableList<SupliyerTm> obList = FXCollections.observableArrayList();

        try {
            List<SupliyerDto> dtoList = model.getAllSupliyer();

            for (SupliyerDto dto : dtoList) {
                obList.add(
                        new SupliyerTm(
                                dto.getSupliyer_id(),
                                dto.getStock_id(),
                                dto.getSupliyer_name(),
                                dto.getTel(),
                                dto.getE_mail(),
                                dto.getAddress()

                        )
                );
            }

            tblSupliyer.setItems(obList);
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
        String supliyer_id = txtSupliyerId.getText();

        var model = new SupliyerModel();
        try {
            boolean isDeleted = model.deleteSupliyer(supliyer_id);
            tblSupliyer.refresh();
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "delectd").show();
                setCellValueFactory();
                loadAllSupliyer();

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();

        }


    }
    private void loadStockIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<StockDto> stockList = StockModel.getAllStock();

            for (StockDto dto : stockList) {
                obList.add(dto.getStock_id());
            }
            cmbStockId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
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

        boolean isSupliyerValidated = validateSupliyer();
        if(isSupliyerValidated) {
            String supliyer_id = txtSupliyerId.getText();
            String stock_id = cmbStockId.getValue();
            String supliyer_name = txtName.getText();
            String tel = txtTel.getText();
            String e_mail = txtEmail.getText();
            String address = txtAdress.getText();

            var dto = new SupliyerDto(supliyer_id, stock_id, supliyer_name, tel, e_mail, address);

            var model = new SupliyerModel();

            try {
                boolean isSaved = model.saveSupliyer(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "supliyer saved!").show();
                    clearFields();
                    setCellValueFactory();
                    loadAllSupliyer();

                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

        }

    }

    private boolean validateSupliyer() {
        int num=0;
        String supliyer_id = txtSupliyerId.getText();

        boolean isSupliyerIdValidated = Pattern.matches("[S][P][0-9]{3,}", supliyer_id);
        if (!isSupliyerIdValidated) {
            vibrateTextField(txtSupliyerId);
           // new Alert(Alert.AlertType.ERROR, "INVALID Id").show();
           num =1;
        }

        String supliyer_name = txtName.getText();
        boolean isSupliyerNameValidated = Pattern.matches("[A-Za-z]{3,}", supliyer_name);
        if (!isSupliyerNameValidated) {
            vibrateTextField(txtName);
           // new Alert(Alert.AlertType.ERROR, "INVALID Name").show();
           num=1;
        }


        String tel = txtTel.getText();
        boolean isSupliyerTelValidated = Pattern.matches("[0][0-9]{10}", tel);
        if (!isSupliyerTelValidated) {
            vibrateTextField(txtTel);
            //new Alert(Alert.AlertType.ERROR, "INVALID Tel").show();
            num=1;
        }

        String e_mail = txtEmail.getText();
        boolean isSupliyerEmailValidated = Pattern.matches("[a-z].*(com|lk)", e_mail);
        if (!isSupliyerEmailValidated) {
            vibrateTextField(txtEmail);
           // new Alert(Alert.AlertType.ERROR, "INVALID Email").show();
            num=1;
        }
        String address = txtAdress.getText();
        boolean isSupliyerAddressValidated = Pattern.matches("[A-Za-z0-9/.\\s]{3,}", address);
        if (!isSupliyerAddressValidated) {
            vibrateTextField(txtAdress);
            //new Alert(Alert.AlertType.ERROR, "INVALID Address").show();
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
        String supliyer_id = txtSupliyerId.getText();
        String stock_id = cmbStockId.getValue();
        String supliyer_name = txtName.getText();
        String tel = txtTel.getText();
        String e_mail = txtEmail.getText();
        String address = txtAdress.getText();

        var dto = new SupliyerDto( supliyer_id, stock_id, supliyer_name,tel , e_mail,address);

        var model = new SupliyerModel();

        try {
            boolean isUpdated = model.updateSupliyer(dto);
            System.out.println(isUpdated);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, " supliyer updated!").show();
                clearFields();
                setCellValueFactory();
                loadAllSupliyer();

            }

        }
        catch(SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }


    }

    @FXML
    void cmbStockIdOnAction(ActionEvent event) {

    }


    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String supliyer_id = txtSupliyerId.getText();
        var model = new SupliyerModel();


        try {
            SupliyerDto dto = model.searchSupliyer(supliyer_id);

            if(dto != null) {
                fillFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "supliyer not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }
    private void fillFields(SupliyerDto dto) {
        txtSupliyerId.setText(dto.getSupliyer_id());
        cmbStockId.setValue(dto.getStock_id());
        txtName.setText(dto.getSupliyer_name());
        txtTel.setText(dto.getTel());
        txtEmail.setText(dto.getE_mail());
        txtAdress.setText(dto.getAddress());


    }
    void clearFields() {
        txtSupliyerId.setText("");
        cmbStockId.setValue("");
        txtName.setText("");
        txtTel.setText("");
        txtEmail.setText("");
        txtAdress.setText("");
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
