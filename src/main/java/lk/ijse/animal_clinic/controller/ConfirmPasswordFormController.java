package lk.ijse.animal_clinic.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lk.ijse.animal_clinic.dto.UserDto;
import lk.ijse.animal_clinic.model.UserModel;

import java.io.IOException;
import java.sql.SQLException;

public class ConfirmPasswordFormController {

    public static String Name;

    public static String id;
    public static String Address;
    public static String Email;
    public static String pswd;

    public static byte[] image;

    private Pane registerPane;

    @FXML
    private Pane bigPane1;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnCreateAcc;

    @FXML
    private AnchorPane mainPane1;

    @FXML
    private Pane smallPane1;

    @FXML
    private TextField txtConfirmPassword;

    @FXML
    private TextField txtPassword;

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/otp_form.fxml"));
        registerPane = fxmlLoader.load();
        mainPane1.getChildren().clear();
        mainPane1.getChildren().setAll(registerPane);
    }

    @FXML
    void btnCreateAccOnAction(ActionEvent event) throws IOException {

        if (txtPassword.getText().equals(txtConfirmPassword.getText())) {

            pswd = txtPassword.getText();


            System.out.println(id+pswd+Name+Address+Email);

            UserDto userDto = new UserDto(Name, pswd,id,Address, Email,image);

            try {
                var user = new UserModel();

                boolean isSaved = user.save(userDto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Registered Successfull !").show();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/login_form.fxml"));
                    registerPane = fxmlLoader.load();
                    mainPane1.getChildren().clear();
                    mainPane1.getChildren().setAll(registerPane);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
            }




        }

    }

    public void values(String name, String id, String address, String email, byte[] image) {
        Name = name;
        this.id = id;
        Address = address;
        Email = email;
        this.image=image;

    }
}
