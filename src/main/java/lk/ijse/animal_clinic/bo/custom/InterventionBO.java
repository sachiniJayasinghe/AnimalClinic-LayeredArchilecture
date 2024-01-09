package lk.ijse.animal_clinic.bo.custom;

import javafx.event.ActionEvent;
import lk.ijse.animal_clinic.bo.SuperBO;
import lk.ijse.animal_clinic.dto.AppointmentDto;
import lk.ijse.animal_clinic.dto.ThreatmentDetailsDto;
import lk.ijse.animal_clinic.dto.TreatementDto;
import lk.ijse.animal_clinic.dto.customerDto;

import java.sql.SQLException;
import java.util.List;

public interface InterventionBO extends SuperBO {
    boolean savePayment(String value, String threatmentId , String loadPayementID, double total) throws SQLException ;

    String getThreatmentId(String value) throws SQLException, ClassNotFoundException;
     customerDto search(String id) throws SQLException, ClassNotFoundException;
     double getCost(String value) throws SQLException, ClassNotFoundException;
     boolean save(ThreatmentDetailsDto dto) throws SQLException, ClassNotFoundException;

     List<AppointmentDto> getAll() throws SQLException, ClassNotFoundException;
     List<TreatementDto> getAllTreatement() throws SQLException, ClassNotFoundException;


     int searchPaymentId() throws SQLException ;


    }
