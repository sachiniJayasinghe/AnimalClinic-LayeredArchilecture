package lk.ijse.animal_clinic.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import lk.ijse.animal_clinic.model.UserModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class SignUpFormController implements Initializable {
    public static OTPFormController otpFormController = new OTPFormController();


    private String name;
    private String user_id;
    private String address;
    private String email;

    private byte [] image;
    @FXML
    private Pane bigPane;

    private Pane registerPane;

    @FXML
    private Button btnBack;

    @FXML
    private ImageView img;


    @FXML
    private Pane paneImg;

    @FXML
    private Button btnNext;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Pane smallPane;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtUserName;



    void values(){
        setName(txtUserName.getText());
        setUser_id(txtID.getText());
        setAddress(txtAddress.getText());
        setEmail(txtEmail.getText());
        setImage(image);
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/login_form.fxml"));
        registerPane = fxmlLoader.load();
        mainPane.getChildren().clear();
        mainPane.getChildren().setAll(registerPane);

    }

    @FXML
    void btnNextOnAction(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/otp_form.fxml"));
        registerPane = fxmlLoader.load();
        mainPane.getChildren().clear();
        mainPane.getChildren().setAll(registerPane);

        otpFormController.otpFormController(txtID.getText(), txtUserName.getText(), txtAddress.getText(), txtEmail.getText(),image);
        sendOTP.sendOtp(txtUserName.getText(), txtEmail.getText());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        values();
    }

    @FXML
    void btnChoosseImgOnAction(ActionEvent event) {
        chooseImage();
    }


    private void chooseImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            UserModel userModel = new UserModel();
            image = convertImageToByteArray(selectedFile);
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
}
