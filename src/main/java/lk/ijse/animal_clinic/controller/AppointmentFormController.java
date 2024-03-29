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
import lk.ijse.animal_clinic.bo.BOFactory;
import lk.ijse.animal_clinic.bo.custom.AppointmentBO;
import lk.ijse.animal_clinic.db.DbConnection;
import lk.ijse.animal_clinic.dto.AppointmentDto;
import lk.ijse.animal_clinic.dto.DoctorDto;
import lk.ijse.animal_clinic.dto.PetDto;
import lk.ijse.animal_clinic.dto.customerDto;
import lk.ijse.animal_clinic.dto.tm.AppointmentTm;
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

    DoctorDto doctorDto;

    AppointmentDto appointmentDto;

    AppointmentBO appointmentBO  =  (AppointmentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.AppointmentBO);
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

        ObservableList<AppointmentTm> obList = FXCollections.observableArrayList();

        try {
            List<AppointmentDto> dtoList = appointmentBO.getAll();

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
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadPetIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<PetDto> cusList = appointmentBO.getAllPets();

            for (PetDto dto : cusList) {
                obList.add(dto.getPet_id());
            }
            cmbPetId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void loadDoctorIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<DoctorDto> cusList = appointmentBO.getAllDoctor();

            for (DoctorDto dto : cusList) {
                obList.add(dto.getId());
            }
            cmbDoctorId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
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

        try {
            boolean isDeleted = appointmentBO.delete(app_id);

            if(isDeleted) {
                tblAppointment.refresh();

                new Alert(Alert.AlertType.CONFIRMATION, "Appointment deleted!").show();
                loadAllAppointment();
                setCellValueFactory();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

        String app_id = txtId.getText();
        String doctor_id = cmbDoctorId.getValue();
        String pet_id = cmbPetId.getValue();
        Date date = java.sql.Date.valueOf(txtDate.getValue());
        Time Time = java.sql.Time.valueOf(txtTime.getText());
        int Number = Integer.parseInt(txtNumber.getText());

        var dto = new AppointmentDto(app_id, doctor_id, pet_id, date, Time, Number);

        try {
            boolean isSaved = appointmentBO.save(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Appointment saved!").show();
                clearFields();
                loadAllAppointment();
                setCellValueFactory();
                generateNextAppointmentId();
                customerDto customerDto = appointmentBO.loadAll(appointmentDto.getApp_id());
                new SendMailForAppointment(doctorDto,appointmentDto,customerDto);
            }
        } catch (SQLException | ClassNotFoundException e) {
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

        try {
            boolean isUpdated = appointmentBO.update(dto);
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

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String app_id = txtId.getText();


        try {
            AppointmentDto dto = appointmentBO.search(app_id);

            if(dto != null) {
                fillFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Appointment not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void cmbDoctorIdOnAction(ActionEvent event) {
        try {
            String doctorID = cmbDoctorId.getValue();
            appointmentDto = appointmentBO.loadNumber(doctorID);

            if (appointmentDto == null) {
                txtNumber.setText("1");
            } else {
                txtNumber.setText(String.valueOf(appointmentDto.getNumber() + 1));
            }

            doctorDto = appointmentBO.loadTime(doctorID);
            System.out.println(doctorDto.getOutTime());

            if (doctorDto != null) {
                Time outTime = doctorDto.getOutTime();

                int num = Integer.parseInt(txtNumber.getText());
                int addTime = num * 15;

                Time comeInTime = doctorDto.getComeInTime();
                LocalTime localComeInTime = comeInTime.toLocalTime();
                LocalTime newTime = localComeInTime.plusMinutes(addTime);
                Time updatedTime = Time.valueOf(newTime);

                if (outTime != null && outTime.before(updatedTime)) {
                    new Alert(Alert.AlertType.ERROR, doctorDto.getName() + " is not available").show();
                    txtNumber.setText("");
                    txtTime.setText("");
                } else {
                    txtTime.setText(String.valueOf(updatedTime));
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Doctor information not found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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
            String appointmentID = appointmentBO.generateNewID();
            txtId.setText(appointmentID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
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
