package lk.ijse.animal_clinic.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import lk.ijse.animal_clinic.db.DbConnection;
import lk.ijse.animal_clinic.dto.*;
import lk.ijse.animal_clinic.dto.tm.DoctorTm;
import lk.ijse.animal_clinic.dto.tm.ThreatmentDetailTm;
import lk.ijse.animal_clinic.model.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class InterventionFormController {
    @FXML
    private Button btnThreatment;

    @FXML
    private Button btnPay;

    private final ObservableList<ThreatmentDetailTm> obList = FXCollections.observableArrayList();

    @FXML
    private Button btnVaccination;

    @FXML
    private Button btnViewBill;

    @FXML
    private JFXComboBox<String> cmbAppointmentID;

    @FXML
    private JFXComboBox<String> cmbThreatmentName;

    @FXML
    private TableView<ThreatmentDetailTm> tblTreatementDetals;

    @FXML
    private TableColumn<?, ?> colAppointmentId;

    @FXML
    private TableColumn<?, ?> colCost;

    @FXML
    private TableColumn<?, ?> colTreatmentId;

    @FXML
    private TableColumn<?, ?> colTreatmentType;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private AnchorPane subPane;


    public static String app_id;

    @FXML
    private TextField txtTotal;

    static double total = 0;


    public void initialize()  {
        loadAppointmentIds();
        loadTreatmentType();
        setAnimation(tblTreatementDetals);
    }

    @FXML
    void btnPayOnAction(ActionEvent event) throws SQLException {

        boolean isPlaced = false;

        var model = new TreatementModel();


        String threatmentId = model.getThreatmentId(cmbThreatmentName.getValue());


        var model1 = new PaymentModel();
        try {
            isPlaced = model1.savePayment(cmbAppointmentID.getValue(),threatmentId,LoadPayementID(), total);
        } catch (SQLException ex) {
            new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
        }
        if(isPlaced) {
            app_id = cmbAppointmentID.getValue();
            new Alert(Alert.AlertType.CONFIRMATION, "ThreatmentDetails SAVED").show();
            cmbThreatmentName.getSelectionModel().clearSelection();
            cmbAppointmentID.getSelectionModel().clearSelection();
            txtTotal.clear();
            tblTreatementDetals.getItems().clear();
            ThreatmentDetailModel ob = new ThreatmentDetailModel();
            String customerId = ob.getCustomerId(app_id);
            var customerModel = new CustomerModel();
            try {
                customerDto dto = customerModel.searchCustomer(customerId);

                if(dto != null) {
                    new sendMail(dto.getName(),app_id, LocalDate.now(), LocalTime.now(),total,dto.getEmail());
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }


            app_id = null;
        } else {
            new Alert(Alert.AlertType.ERROR, "ThreatmentDetails Not SAVED").show();
        }
    }




    @FXML
    void btnThreatmentOnAction(ActionEvent event) throws IOException {
        setUI("threatment_form.fxml");

    }

    @FXML
    void btnVaccinationOnAction(ActionEvent event) throws IOException {
        setUI("vaccination_form.fxml");
    }

    private void setUI(String fileName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/" + fileName));
        Pane registerPane = fxmlLoader.load();

        try {
            mainPane.getChildren().clear();
            mainPane.getChildren().setAll(registerPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void btnViewBillOnAction(ActionEvent event) throws SQLException {
        try {
            JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/reports/PaymentReport.jrxml");
            JRDesignQuery query = new JRDesignQuery();
            query.setText("SELECT p_id, app_id, amount\n" +
                    "FROM payment\n" +
                    "ORDER BY p_id DESC\n" +
                    "LIMIT 1;");
            jasperDesign.setQuery(query);

            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DbConnection.getInstance().getConnection());

            JFrame frame = new JFrame("Jasper Report Viewer");
            JRViewer viewer = new JRViewer(jasperPrint);

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.getContentPane().add(viewer);
            frame.setSize(new Dimension(1200, 800));
            frame.setVisible(true);
        } catch (JRException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void cmbAppointmentIdOnAction(ActionEvent event) {

    }

    @FXML
    void cmbThreatmentNameOnAction(ActionEvent event) throws SQLException {
        var model = new TreatementModel();

        double num = model.getCost(cmbThreatmentName.getValue());
        total += num;
        String threatmentId = model.getThreatmentId(cmbThreatmentName.getValue());
        System.out.println(threatmentId);
        txtTotal.setText(String.valueOf(total));

        var dto = new ThreatmentDetailsDto(
                cmbAppointmentID.getValue(),
                threatmentId,
                num,
                cmbThreatmentName.getValue(),
                0.0
        );

        var model1 = new ThreatmentDetailModel();

        try {
            boolean isSaved = model1.saveThreatmentDetails(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "ThreatmentDetails SAVED").show();
                loadThreatmentDetails(cmbAppointmentID.getValue(), threatmentId, num, cmbThreatmentName.getValue(), total);
                setCellValueFactory();

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadThreatmentDetails(String id, String threatmentId, double cost, String type, double total) {
        obList.add(new ThreatmentDetailTm(
                id,
                threatmentId,
                cost,
                type,
                0.0
        ));

        tblTreatementDetals.setItems(obList);

    }

    private void setCellValueFactory() {
        colTreatmentId.setCellValueFactory(new PropertyValueFactory<>("treatementId"));
        colAppointmentId.setCellValueFactory(new PropertyValueFactory<>("AppointmentId"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("Cost"));
        colTreatmentType.setCellValueFactory(new PropertyValueFactory<>("Type"));
    }

    private void loadAppointmentIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<AppointmentDto> appList = AppointmentModel.getAllAppointment();

            for (AppointmentDto dto : appList) {
                obList.add(dto.getApp_id());
            }
            cmbAppointmentID.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadTreatmentType() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<TreatementDto> appList = TreatementModel.getAllTreatement();

            for (TreatementDto dto : appList) {
                obList.add(dto.getType());
            }
            cmbThreatmentName.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String LoadPayementID() throws SQLException {
        int id = PaymentModel.search();
        int idl = id+1;
        String id1 = String.valueOf(idl);
        System.out.println(idl);
        return id1;
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
