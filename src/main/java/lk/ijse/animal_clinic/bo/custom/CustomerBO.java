package lk.ijse.animal_clinic.bo.custom;

import lk.ijse.animal_clinic.dto.customerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO {
    ArrayList<customerDto> getAll() throws SQLException, ClassNotFoundException ;

    boolean save(final customerDto dto) throws SQLException, ClassNotFoundException ;
    boolean update(final customerDto dto) throws SQLException, ClassNotFoundException ;

    boolean exist(String id) throws SQLException, ClassNotFoundException ;
    boolean delete(String id) throws SQLException, ClassNotFoundException ;
    String generateNewID() throws SQLException, ClassNotFoundException ;
    customerDto search(String id) throws SQLException, ClassNotFoundException ;
     int getCustomerCount() throws ClassNotFoundException, SQLException;

    }
