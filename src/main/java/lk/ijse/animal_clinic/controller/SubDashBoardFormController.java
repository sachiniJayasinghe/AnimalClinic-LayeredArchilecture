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

        }
        try {
            loadPetCount();
        } catch (SQLException e) {


        }
        try {
            loadDoctorCount();
        } catch (SQLException e) {


        }

        transitionRightToLeft(paneCustomerCount);
        transitionRightToLeft(paneDoctorsCount);
        transitionRightToLeft(paneParagraph);
        transitionRightToLeft(paneImage);
        transitionRightToLeft(panePetsCount);

    }


    public void loadCustomerCount() throws SQLException {

        var model = new CustomerModel();
        int count = model.getCustomerCount();
        lblCustomerCount.setText(String.valueOf(count));
    }

    public void loadPetCount() throws SQLException {

        var model = new PetModel();
        int count = model.getPetCount();
        lblPetCount.setText(String.valueOf(count));
    }

    public void loadDoctorCount() throws SQLException {

        var model = new DoctorModel();
        int count = model.getDoctorCount();
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