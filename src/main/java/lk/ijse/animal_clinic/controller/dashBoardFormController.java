package lk.ijse.animal_clinic.controller;

import javafx.event.ActionEvent;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class dashBoardFormController implements Initializable {
    @FXML
    private Button btnCutomer;

    @FXML
    private ImageView imgProfileSettings;
    @FXML
    private Button btnProfile;

    @FXML
    private Button btnDashBoard;

    @FXML
    private Button btnDoctor;

    @FXML
    private Button btnEmployee;

    @FXML
    private Button btnPet;

    @FXML
    private Button btnSalary;

    @FXML
    private Button btnIntervention;

    @FXML
    private Button btnSupplier;


    @FXML
    private Button btnAppointment;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private AnchorPane subPane;



    @FXML
    void btnCutomerOnAction(ActionEvent event) throws IOException {
        setUI("customer_form.fxml");
        addActionButtonColor(btnCutomer);
        cancelbuttonColor(btnDashBoard,btnDoctor,btnPet,btnSalary,btnIntervention,btnEmployee,btnSupplier,btnAppointment);

    }

    @FXML
    void btnDashBoardOnAction(ActionEvent event) throws IOException {
        setUI("subDashBoardForm.fxml");
        addActionButtonColor(btnDashBoard);
        cancelbuttonColor(btnCutomer,btnDoctor,btnPet,btnSalary,btnIntervention,btnEmployee,btnSupplier,btnAppointment);
    }

    @FXML
    void btnDoctorOnAction(ActionEvent event) throws IOException {
        setUI("doctor_form.fxml");
        addActionButtonColor(btnDoctor);
        cancelbuttonColor(btnDashBoard,btnCutomer,btnPet,btnSalary,btnIntervention,btnEmployee,btnSupplier,btnAppointment);
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) throws IOException {
        setUI("employee_form.fxml");
        addActionButtonColor(btnEmployee);
        cancelbuttonColor(btnDashBoard,btnDoctor,btnPet,btnSalary,btnIntervention,btnCutomer,btnSupplier,btnAppointment);
    }

    @FXML
    void btnPetOnAction(ActionEvent event) throws IOException {
        setUI("pet_form.fxml");
        addActionButtonColor(btnPet);
        cancelbuttonColor(btnDashBoard,btnDoctor,btnCutomer,btnSalary,btnIntervention,btnEmployee,btnSupplier,btnAppointment);
    }

    @FXML
    void btnSalaryOnAction(ActionEvent event) throws IOException {
        setUI("salary_form.fxml");
        addActionButtonColor(btnSalary);
        cancelbuttonColor(btnDashBoard,btnDoctor,btnPet,btnCutomer,btnIntervention,btnEmployee,btnSupplier,btnAppointment);
    }


    @FXML
    void btnAppointmentOnAction(ActionEvent event) throws IOException {
        setUI("appointment_form.fxml");
        addActionButtonColor(btnAppointment);
        cancelbuttonColor(btnDashBoard,btnDoctor,btnPet,btnCutomer,btnIntervention,btnEmployee,btnSupplier,btnSalary);
    }

    @FXML
    void btnInterventionOnAction(ActionEvent event) throws IOException {
        setUI("intervention_form.fxml");
        addActionButtonColor(btnIntervention);
        cancelbuttonColor(btnDashBoard,btnDoctor,btnPet,btnSalary,btnCutomer,btnEmployee,btnSupplier,btnAppointment);
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) throws IOException {
        setUI("stock_form.fxml");
        addActionButtonColor(btnSupplier);
        cancelbuttonColor(btnDashBoard,btnDoctor,btnPet,btnSalary,btnIntervention,btnEmployee,btnCutomer,btnAppointment);
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
        btn.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 0%, #56ADF9, #FFFFFF);  -fx-border-width: 0 0 0 7px; -fx-border-color: white; -fx-text-fill: black;");

    }

    void cancelbuttonColor(Button btn1,Button btn2,Button btn3,Button btn4,Button btn5,Button btn6,Button btn7,Button btn8){
        btn1.setStyle("   -fx-background-color: #035077;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 5, 0, 3, 3);");
        btn2.setStyle("   -fx-background-color: #035077;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 5, 0, 3, 3);");
        btn3.setStyle("   -fx-background-color: #035077;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 5, 0, 3, 3);");
        btn4.setStyle("   -fx-background-color: #035077;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 5, 0, 3, 3);");
        btn5.setStyle("   -fx-background-color: #035077;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 5, 0, 3, 3);");
        btn6.setStyle("   -fx-background-color: #035077;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 5, 0, 3, 3);");
        btn7.setStyle("   -fx-background-color: #035077;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 5, 0, 3, 3);");
        btn8.setStyle("   -fx-background-color: #035077;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 5, 0, 3, 3);");


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setUI("subDashBoardForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        addActionButtonColor(btnDashBoard);
        cancelbuttonColor(btnCutomer,btnDoctor,btnPet,btnSalary,btnIntervention,btnEmployee,btnSupplier,btnAppointment);
    }

    @FXML
    void btnProfileOnAction(ActionEvent event) throws IOException {
        setUI("profileDashBoard.fxml");
    }


    @FXML
    void settingbuttonOnMoved(MouseEvent event) {
        new animatefx.animation.RotateIn(imgProfileSettings).play();
    }

}
