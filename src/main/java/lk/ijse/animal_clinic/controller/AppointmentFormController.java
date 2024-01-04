package lk.ijse.animal_clinic.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import lk.ijse.animal_clinic.db.DbConnection;
import lk.ijse.animal_clinic.dto.AppointmentDto;
import lk.ijse.animal_clinic.dto.DoctorDto;
import lk.ijse.animal_clinic.dto.PetDto;
import lk.ijse.animal_clinic.dto.customerDto;
import lk.ijse.animal_clinic.dto.tm.AppointmentTm;
import lk.ijse.animal_clinic.dto.tm.CustomerTm;
import lk.ijse.animal_clinic.model.AppointmentModel;
import lk.ijse.animal_clinic.model.CustomerModel;
import lk.ijse.animal_clinic.model.DoctorModel;
import lk.ijse.animal_clinic.model.PetModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
public class AppointmentFormController {

    @FXML
    private JFXButton btnReport;
    @FXML
    private JFXButton btnClear;

    @FXML
    private TableColumn<?, ?> colNumber;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colAppId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colDoctorId;

    @FXML
    private TableColumn<?, ?> colPetId;

    @FXML
    private TableColumn<?, ?> colTime;

    @FXML
    private TableView<AppointmentTm> tblAppointment;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtDoctorId;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtPetId;

    @FXML
    private TextField txtNumber;

    @FXML
    private TextField txtTime;
    @FXML
    private JFXComboBox<String> cmbDoctorId;

    @FXML
    private JFXComboBox<String> cmbPetId;

    DoctorModel doctorModel;

    DoctorDto doctorDto;

    AppointmentDto appointmentDto;

    public void initialize() {
        setCellValueFactory();
        loadAllAppointment();
        loadPetIds();
        loadDoctorIds();
        generateNextAppointmentId();
        setAnimation(tblAppointment);
    }
    private void setCellValueFactory() {
        colAppId.setCellValueFactory(new PropertyValueFactory<>("app_id"));
        colDoctorId.setCellValueFactory(new PropertyValueFactory<>("doctor_id"));
        colPetId.setCellValueFactory(new PropertyValueFactory<>("pet_id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime .setCellValueFactory(new PropertyValueFactory<>("time"));
        colNumber.setCellValueFactory(new PropertyValueFactory<>("Number"));
    }
    private void loadAllAppointment() {
        var model = new AppointmentModel();

        ObservableList<AppointmentTm> obList = FXCollections.observableArrayList();

        try {
            List<AppointmentDto> dtoList = model.getAllAppointment();

            for(AppointmentDto dto : dtoList) {
                obList.add(
                        new AppointmentTm(
                                dto.getApp_id(),
                                dto.getDoctor_id(),
                                dto.getPet_id(),
                                dto.getDate(),
                                dto.getTime(),
                                dto.getNumber()

                        )
                );
            }

            tblAppointment.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadPetIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<PetDto> cusList = PetModel.getAllPet();

            for (PetDto dto : cusList) {
                obList.add(dto.getPet_id());
            }
            cmbPetId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadDoctorIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<DoctorDto> cusList = DoctorModel.getAllDoctor();

            for (DoctorDto dto : cusList) {
                obList.add(dto.getId());
            }
            cmbDoctorId.setItems(obList);
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
        String app_id = txtId.getText();

        var AppointmentModel  = new AppointmentModel();
        try {
            boolean isDeleted = AppointmentModel.deleteAppointment(app_id);

            if(isDeleted) {
                tblAppointment.refresh();

                new Alert(Alert.AlertType.CONFIRMATION, "Appointment deleted!").show();
                loadAllAppointment();
                setCellValueFactory();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

        String app_id = txtId.getText();
        String doctor_id = cmbDoctorId.getValue();
        String pet_id = cmbPetId.getValue();
        Date Date = java.sql.Date.valueOf(txtDate.getValue());
        Time Time = java.sql.Time.valueOf(LocalTime.now());
        int Number = Integer.parseInt(txtNumber.getText());

        var dto = new AppointmentDto(app_id, doctor_id, pet_id, Date, Time, Number);

        var model = new AppointmentModel();

        try {
            boolean isSaved = model.saveAppointment(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Appointment saved!").show();
                clearFields();
                loadAllAppointment();
                setCellValueFactory();
                generateNextAppointmentId();
                CustomerModel customerModel = new CustomerModel();
                customerDto customerDto = customerModel.loadAll(appointmentDto.getApp_id());
                new SendMailForAppointment(doctorDto,appointmentDto,customerDto);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }

    }



    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String app_id = txtId.getText();
        String doctor_id = cmbDoctorId.getValue();
        String pet_id = cmbPetId.getValue();
        Date Date = java.sql.Date.valueOf(txtDate.getValue());
        Time Time = java.sql.Time.valueOf(LocalTime.now());
        int Number = Integer.parseInt(txtNumber.getText());

        var dto = new AppointmentDto(app_id, doctor_id, pet_id, Date, Time, Number);


        var model = new AppointmentModel();

        try {
            boolean isUpdated = model.updateAppointment(dto);
            System.out.println(isUpdated);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, " Appointment updated!").show();
                clearFields();
                loadAllAppointment();
                setCellValueFactory();

            }

        }
        catch(SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }


    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String app_id = txtId.getText();


        var model = new AppointmentModel();
        try {
            AppointmentDto dto = model.searchAppointment(app_id);

            if(dto != null) {
                fillFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Appointment not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }
    @FXML
    void cmbDoctorIdOnAction(ActionEvent event) {
        try {


            AppointmentModel appointmentModel = new AppointmentModel();
            String doctorID = cmbDoctorId.getValue();;
            appointmentDto = appointmentModel.loadNumber(doctorID);
            if (appointmentDto==null){
                txtNumber.setText("1");
            }else{
                txtNumber.setText(String.valueOf(appointmentDto.getNumber()+1));
            }
            doctorModel = new DoctorModel();
            doctorDto = doctorModel.loadTime(doctorID);
            Time outTime = doctorDto.getOutTime();

            int num = Integer.parseInt(txtNumber.getText());
            int addTime = num*(15);
            Time time = doctorDto.getComeInTime();
            LocalTime localTime = time.toLocalTime();
            LocalTime newTime = localTime.plusMinutes(addTime);
            Time updatedTime = Time.valueOf(newTime);
            txtTime.setText(String.valueOf(updatedTime));


            if (outTime != null && outTime.before(updatedTime)) {
                new Alert(Alert.AlertType.ERROR, doctorDto.getName() + " is not available").show();
                txtNumber.setText("");
                txtTime.setText("");
            } else {
                txtTime.setText(String.valueOf(updatedTime));
            }



        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void cmbPetIdOnAction(ActionEvent event) {

    }
    private void fillFields(AppointmentDto dto) {
        txtId.setText(dto.getApp_id());
        txtTime.setText(String.valueOf(dto.getTime()));
        txtDate.setValue(dto.getDate().toLocalDate());
        cmbDoctorId.setValue(dto.getDoctor_id());
        cmbPetId.setValue(dto.getPet_id());
        txtNumber.setText(String.valueOf(dto.getNumber()));

    }


    void clearFields() {
        txtId.setText("");
        txtTime.setText("");
        cmbPetId.setValue("");

    }

    private void generateNextAppointmentId() {
        try {
            AppointmentModel appointmentModel = new AppointmentModel();
            String appointmentID = appointmentModel.generateNextOrderId();
            txtId.setText(appointmentID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnReportOnAction(ActionEvent event) throws SQLException {

        try {
            JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/reports/Appointment_Report.jrxml");
            JRDesignQuery query = new JRDesignQuery();
            query.setText("SELECT\n" +
                    "    c.*,\n" +
                    "    p.*,\n" +
                    "    d.*,\n" +
                    "    a.*\n" +
                    "FROM\n" +
                    "    appointements a\n" +
                    "JOIN pet p ON a.pet_id = p.pet_id\n" +
                    "JOIN customer c ON p.cus_id = c.cus_id\n" +
                    "JOIN doctor d ON a.doctor_id = d.doctor_id\n" +
                    "ORDER BY\n" +
                    "    a.app_id DESC\n" +
                    "LIMIT 1;\n;");
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
