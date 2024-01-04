package lk.ijse.animal_clinic.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.ijse.animal_clinic.Launcher;

import java.io.IOException;

public class ProfileDashBoardController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnChngPswd;

    @FXML
    private Button btnEditProfile;

    @FXML
    private Button btnLogOut;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private AnchorPane subPane;

    @FXML
    void initialize() throws IOException {
        addActionButtonColor(btnEditProfile);
        cancelbuttonColor(btnBack,btnChngPswd,btnLogOut);
        setUI("editProfil_form.fxml");
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        addActionButtonColor(btnBack);
        cancelbuttonColor(btnChngPswd,btnEditProfile,btnLogOut);
        setUI("subDashBoardForm.fxml");

    }

    @FXML
    void btnChngPswdOnAction(ActionEvent event) throws IOException {
        addActionButtonColor(btnChngPswd);
        cancelbuttonColor(btnBack,btnEditProfile,btnLogOut);
        setUI("changePassword_form.fxml");

    }

    @FXML
    void btnEditProfileOnAction(ActionEvent event) throws IOException {
        addActionButtonColor(btnEditProfile);
        cancelbuttonColor(btnBack,btnChngPswd,btnLogOut);
        setUI("editProfil_form.fxml");
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) throws Exception {
        new Launcher().start(new Stage());
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        stage.close();

    }

    private void setUI(String fileName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/" + fileName));
        Pane registerPane = fxmlLoader.load();

        try {
            subPane.getChildren().clear();
            subPane.getChildren().setAll(registerPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void addActionButtonColor(Button btn){
        btn.setStyle("-fx-background-color: linear-gradient(from 0% 100% to 0% 0%, #00505B, #8ecae6); -fx-border-width: 0 0 4px 0; -fx-border-color: black; -fx-text-fill: black;");

    }

    void cancelbuttonColor(Button btn1,Button btn2,Button btn3){
        btn1.setStyle("-fx-background-color: #8ecae6");
        btn2.setStyle("-fx-background-color: #8ecae6");
        btn3.setStyle("-fx-background-color: #8ecae6");
    }
}