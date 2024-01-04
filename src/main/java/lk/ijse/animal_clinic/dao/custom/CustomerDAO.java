package lk.ijse.animal_clinic.dao.custom;

import lk.ijse.animal_clinic.dao.CrudDAO;
import lk.ijse.animal_clinic.db.DbConnection;
import lk.ijse.animal_clinic.dto.customerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerDAO  extends CrudDAO<customerDto> {

    customerDto loadAll(String appId) throws SQLException, ClassNotFoundException ;
    public int getCustomerCount() throws SQLException, ClassNotFoundException;





    }

