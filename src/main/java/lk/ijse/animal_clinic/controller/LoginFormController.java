package lk.ijse.animal_clinic.controller;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import lk.ijse.animal_clinic.dto.UserDto;
import lk.ijse.animal_clinic.model.UserModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {


    @FXML
    private Button btnSignUp;


    @FXML
    private Pane bigPane;

    @FXML
    private Pane smallPane;

    private Pane registerPane;

    @FXML
    private Button btnLogin;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private PasswordField txtPasswprd;

    @FXML
    private TextField txtUserName;

    public static String password;

    public static String name;


    public static String address;



    public static String email;

    public static String userID;

    String user;
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
    void btnLoginOnAction(ActionEvent event) throws IOException {

        try {
            var model = new UserModel();
            UserDto user1 = model.search(txtUserName.getText());
            if (user1 != null) {
                password = user1.getPassword();
                user = user1.getName();
                userID = user1.getId();
                address = user1.getAddress();
                email = user1.getEmail();
                name = user1.getName();
                System.out.println(password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact software company").show();
        }

        if (txtUserName.getText().equals(user) && txtPasswprd.getText().equals(password)) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/dashBoard_form.fxml"));
            registerPane = fxmlLoader.load();
            mainPane.getChildren().clear();
            mainPane.getChildren().setAll(registerPane);

        }else {
            if(!txtUserName.getText().equals(user) ){
                vibrateTextField(txtUserName);

            }else {
                vibrateTextField(txtPasswprd);

            }
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TranslateTransition slideIn = new TranslateTransition(Duration.seconds(3), bigPane);
        slideIn.setFromX(600);
        slideIn.setToX(0);
        slideIn.play();
        TranslateTransition slideIn1 = new TranslateTransition(Duration.seconds(3), smallPane);
        slideIn1.setFromX(-600);
        slideIn1.setToX(0);
        slideIn1.play();

    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/signUp_form.fxml"));
        registerPane = fxmlLoader.load();
        mainPane.getChildren().clear();
        mainPane.getChildren().setAll(registerPane);
    }

}
