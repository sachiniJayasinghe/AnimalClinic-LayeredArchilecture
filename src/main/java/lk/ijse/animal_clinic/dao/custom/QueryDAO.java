package lk.ijse.animal_clinic.dao.custom;

import lk.ijse.animal_clinic.dao.SuperDAO;
import lk.ijse.animal_clinic.dto.customerDto;

import java.sql.SQLException;

public interface QueryDAO extends SuperDAO {
    String getCustomerId(String value) throws SQLException, ClassNotFoundException;

    customerDto loadAll(String appId) throws SQLException, ClassNotFoundException;
}
