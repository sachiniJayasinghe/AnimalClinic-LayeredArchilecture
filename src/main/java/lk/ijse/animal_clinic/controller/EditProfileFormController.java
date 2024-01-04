package lk.ijse.animal_clinic.controller;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import lk.ijse.animal_clinic.dto.UserDto;
import lk.ijse.animal_clinic.model.UserModel;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditProfileFormController implements Initializable {
    @FXML
    private JFXButton btnUpdate;

    @FXML
    private ImageView img;

    @FXML
    private Pane paneImg;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtUserName;

    @FXML
    private JFXButton btnChoosseImg;

    private File selectedFile;

    byte[] imageData;


    @FXML
    void btnChoosseImgOnAction(ActionEvent event) {

        chooseImage();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {
        String name = txtUserName.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        byte[] image = imageData;

        UserDto userDto = new UserDto(LoginFormController.userID, LoginFormController.password, name, address, email, image);
        UserModel userModel = new UserModel();


        try {
            boolean isUpdated = userModel.updateUser(userDto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated Successfully").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void chooseImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            System.out.println("Selected Image: " + selectedFile.getAbsolutePath());
            UserModel userModel = new UserModel();
            imageData = convertImageToByteArray(selectedFile);
            Image image = new Image(selectedFile.toURI().toString());
            img.setImage(image);
            img.setFitWidth(200.0);
            img.setFitHeight(200.0);
        } else {
            System.out.println("No image selected.");
        }
    }

    private byte[] convertImageToByteArray(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] imageData = new byte[fis.available()];
            fis.read(imageData);
            return imageData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loardImg();
        txtAddress.setText(LoginFormController.address);
        txtEmail.setText(LoginFormController.email);
        txtUserName.setText(LoginFormController.name);
    }

    private void loardImg() {
        try {
            UserModel userModel = new UserModel();
            System.out.println(LoginFormController.userID);
            byte[] image = userModel.loadImg(LoginFormController.userID);

            System.out.println(image);

            if (image != null) {
                Image loadedImage = convertByteArrayToImage(image);
                img.setImage(loadedImage);
                img.setFitWidth(200.0);
                img.setFitHeight(200.0);
            } else {
                System.out.println("No image found in the database for the user.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private Image convertByteArrayToImage(byte[] imageData) {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(imageData)) {
            return new Image(bis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
