package lk.ijse.animal_clinic.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import lk.ijse.animal_clinic.dto.StockDto;
import lk.ijse.animal_clinic.dto.tm.StockTm;
import lk.ijse.animal_clinic.model.StockModel;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;


public class StockFormController {
    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private Button btnSupplier;

    @FXML
    private AnchorPane stockPane;

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colStockId;

    @FXML
    private TableView<StockTm> tblStock;

    @FXML
    private TextField txtCategory;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtStockId;
    public void initialize() {
        setCellValueFactory();
        loadAllStock();
        setAnimation(tblStock);


    }
    private void setCellValueFactory() {
        colStockId.setCellValueFactory(new PropertyValueFactory<>("stock_id"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }




    private void loadAllStock() {
        var model = new StockModel();

        ObservableList<StockTm> obList = FXCollections.observableArrayList();

        try {
            List<StockDto> dtoList = model.getAllStock();

            for(StockDto dto : dtoList) {
                obList.add(
                        new StockTm(
                                dto.getStock_id(),
                                dto.getCategory(),
                                dto.getDate()


                        )
                );
            }

            tblStock.setItems(obList);
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
        String stock_id = txtStockId.getText();
        var StockModel  = new StockModel();


        try {
            boolean isDeleted = StockModel.StockModel(stock_id);

            if(isDeleted) {
                tblStock.refresh();

                new Alert(Alert.AlertType.CONFIRMATION, " deleted!").show();
                setCellValueFactory();
                loadAllStock();
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
        boolean isValidate = validatedStock();
        if (isValidate) {
            String stock_id = txtStockId.getText();
            String category = txtCategory.getText();
            Date date = java.sql.Date.valueOf(txtDate.getValue());

            var dto = new StockDto(stock_id, category, date);

            var model = new StockModel();

            try {
                boolean isSaved = model.saveStock(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, " saved!").show();
                    clearFields();
                    setCellValueFactory();
                    loadAllStock();

                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

            }
        }
    }
        private boolean validatedStock () {
        int num =0;
            String stock_id = txtStockId.getText();
            boolean isStockIdValidated = Pattern.matches("[S][T][0-9]{3,}",stock_id );
            if (!isStockIdValidated) {
                vibrateTextField(txtStockId);
                //new Alert(Alert.AlertType.ERROR, "INVALID Stock id").show();
                num =1;
            }
            String category = txtCategory.getText();
            boolean isCategoryNameValidated = Pattern.matches("[A-Za-z]{3,}",category);
            if (!isCategoryNameValidated) {
                vibrateTextField(txtCategory);
             //   new Alert(Alert.AlertType.ERROR, "INVALID name").show();
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
        String stock_id = txtStockId.getText();
        String category = txtCategory.getText();
        Date date = java.sql.Date.valueOf(txtDate.getValue());

        var dto = new StockDto(stock_id,category,date);

        var model = new StockModel();

        try {
            boolean isUpdated = model.updateStock(dto);
            System.out.println(isUpdated);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "  updated!").show();
                clearFields();
                setCellValueFactory();
                loadAllStock();

            }
        }
        catch(SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String stock_id = txtStockId.getText();

        var model = new StockModel();
        try {
            StockDto dto = model.searchStock(stock_id);

            if(dto != null) {
                fillFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, " not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }

    }
    private void fillFields(StockDto dto) {
        txtStockId.setText(dto.getStock_id());
        txtCategory.setText(dto.getCategory());
        txtDate.setValue(dto.getDate().toLocalDate());

    }
    void clearFields() {
        txtStockId.setText("");
        txtCategory .setText("");
    }


    @FXML
    void btnSupplierOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/supplier_form.fxml"));
        Pane registerPane = fxmlLoader.load();

        try {
            stockPane.getChildren().clear();
            stockPane.getChildren().setAll(registerPane);
        } catch (Exception e) {
            e.printStackTrace();
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
