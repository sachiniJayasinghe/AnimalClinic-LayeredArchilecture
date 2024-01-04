package lk.ijse.animal_clinic.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import lk.ijse.animal_clinic.model.UserModel;

import java.sql.SQLException;

public class ChangePasswordFormController {
    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TextField txtConfirmPswd;

    @FXML
    private TextField txtPswd;

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (txtPswd.getText().equals(LoginFormController.password)) {

            try {
                UserModel userModel = new UserModel();
                boolean isUpdated = userModel.updatePassword(txtConfirmPswd.getText(), LoginFormController.userID);
            }catch (SQLException e){

            }
        } else {
            System.out.println("Password Not Matched");
            vibrateTextField(txtPswd);
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

    }
}
