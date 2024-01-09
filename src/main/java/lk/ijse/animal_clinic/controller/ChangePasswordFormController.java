package lk.ijse.animal_clinic.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import lk.ijse.animal_clinic.bo.BOFactory;
import lk.ijse.animal_clinic.bo.ChangePasswordBOImpl;
import lk.ijse.animal_clinic.bo.custom.ChangePasswordBO;
import lk.ijse.animal_clinic.model.UserModel;

import java.sql.SQLException;

public class ChangePasswordFormController {
    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TextField txtConfirmPswd;

    @FXML
    private TextField txtPswd;
    ChangePasswordBO changePasswordBO =  (ChangePasswordBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ChangePasswordBO);

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (txtPswd.getText().equals(LoginFormController.password)) {

            try {
                boolean isUpdated = changePasswordBO.updatePassword(txtConfirmPswd.getText(), LoginFormController.userID);
            }catch (SQLException e){

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
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
