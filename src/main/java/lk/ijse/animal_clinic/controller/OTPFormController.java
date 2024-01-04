package lk.ijse.animal_clinic.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;


public class OTPFormController {

    public static String name;
    public static String num;

    public static String address;

    public static String email;

    public static int randNum;

    public static byte [] image;

    public static ConfirmPasswordFormController sign = new ConfirmPasswordFormController();


    private  Pane registerPane;


    @FXML
    private Pane bigPane;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnNext;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Pane smallPane;

    @FXML
    private TextField txtOTP;


    public void otpFormController(String text, String text1, String text2, String text3,byte[] image) {
        name =text;
        num = text1;
        address = text2;
        email = text3;
        this.image=image;

    }

    public void randNum(int randNum) {
        this.randNum = randNum;

    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/signUp_form.fxml"));
        registerPane = fxmlLoader.load();
        mainPane.getChildren().clear();
        mainPane.getChildren().setAll(registerPane);
    }

    @FXML
    void btnNextOnAction(ActionEvent event) throws IOException {

        int rand = Integer.parseInt(txtOTP.getText());

        if (rand == randNum) {

            sign.values(name, num, address, email,image);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/confirmPassword_form.fxml"));
            registerPane = fxmlLoader.load();
            mainPane.getChildren().clear();
            mainPane.getChildren().setAll(registerPane);
        }

    }
}
