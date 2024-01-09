package lk.ijse.animal_clinic.dao.custom;

import lk.ijse.animal_clinic.dao.CrudDAO;
import lk.ijse.animal_clinic.dto.PaymentDto;

import java.sql.SQLException;

public interface PaymentDAO extends CrudDAO<PaymentDto> {
     boolean savePaymentforThreatment(String value, String loadPayementID, double total) throws SQLException;
    int searchID(String id) throws SQLException, ClassNotFoundException ;
    public  int searchPaymentId() throws SQLException ;

    }
