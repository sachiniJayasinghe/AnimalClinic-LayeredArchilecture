package lk.ijse.animal_clinic.controller;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import lk.ijse.animal_clinic.bo.BOFactory;
import lk.ijse.animal_clinic.bo.CustomerBOImpl;
import lk.ijse.animal_clinic.bo.DoctorBOImpl;
import lk.ijse.animal_clinic.bo.PetBOImpl;
import lk.ijse.animal_clinic.bo.custom.CustomerBO;
import lk.ijse.animal_clinic.bo.custom.DoctorBO;
import lk.ijse.animal_clinic.bo.custom.PetBO;
import lk.ijse.animal_clinic.model.CustomerModel;
import lk.ijse.animal_clinic.model.DoctorModel;
import lk.ijse.animal_clinic.model.PetModel;
import lombok.SneakyThrows;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class SubDashBoardFormController implements Initializable {
    @FXML
    private Label lblCustomerCount;
    @FXML
    private Label lblDoctorCount;

    @FXML
    private Label lblPetCount;

    @FXML
    private Label lblTime;
    @FXML
    private Pane paneCustomerCount;
    @FXML
    private Pane paneDoctorsCount;
    @FXML
    private Pane paneImage;
    @FXML
    private Pane paneParagraph;
    @FXML
    private Pane panePetsCount;

    CustomerBO customerBO =  (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CustomerBO);

    PetBO petBO = (PetBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PetBO);

    DoctorBO doctorBO = (DoctorBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.DoctorBO);



    void loadTime(){
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formattedTime = currentTime.format(formatter);
            lblTime.setText(formattedTime);
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTime();
        try {
            loadCustomerCount();
        } catch (SQLException e) {

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            loadPetCount();
        } catch (SQLException | ClassNotFoundException e) {


        }
        try {
            loadDoctorCount();
        } catch (SQLException | ClassNotFoundException e) {


        }

        transitionRightToLeft(paneCustomerCount);
        transitionRightToLeft(paneDoctorsCount);
        transitionRightToLeft(paneParagraph);
        transitionRightToLeft(paneImage);
        transitionRightToLeft(panePetsCount);

    }


    public void loadCustomerCount() throws SQLException, ClassNotFoundException {

        int count = customerBO.getCustomerCount();
        lblCustomerCount.setText(String.valueOf(count));
    }

    public void loadPetCount() throws SQLException, ClassNotFoundException {

        int count = petBO.getPetCount();
        lblPetCount.setText(String.valueOf(count));
    }

    public void loadDoctorCount() throws SQLException, ClassNotFoundException {

        int count = doctorBO.getDoctorCount();
        lblDoctorCount.setText(String.valueOf(count));
    }


    public void transitionRightToLeft(Pane subsubPane){

        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.7), subsubPane);
        scaleTransition.setFromX(0);
        scaleTransition.setFromY(0);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.7), subsubPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);

        scaleTransition.setOnFinished(event -> fadeTransition.play());
        scaleTransition.play();
    }
}