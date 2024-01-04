package lk.ijse.animal_clinic.bo.custom;

import lk.ijse.animal_clinic.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO {
    ArrayList<EmployeeDto> getAll() throws SQLException, ClassNotFoundException ;
    boolean save(final EmployeeDto dto) throws SQLException, ClassNotFoundException ;
    boolean update(final EmployeeDto dto) throws SQLException, ClassNotFoundException ;

    boolean exist(String id) throws SQLException, ClassNotFoundException ;
    boolean delete(String id) throws SQLException, ClassNotFoundException ;
    String generateNewID() throws SQLException, ClassNotFoundException ;
    EmployeeDto search(String id) throws SQLException, ClassNotFoundException ;
}
