package lk.ijse.animal_clinic.bo.custom;

import lk.ijse.animal_clinic.bo.SuperBO;
import lk.ijse.animal_clinic.dto.EmployeeDto;
import lk.ijse.animal_clinic.dto.SalaryDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SalaryBO extends SuperBO {
    ArrayList<SalaryDto> getAll() throws SQLException, ClassNotFoundException ;
    boolean save(final SalaryDto dto) throws SQLException, ClassNotFoundException ;
    boolean update(final SalaryDto dto) throws SQLException, ClassNotFoundException ;

    boolean exist(String id) throws SQLException, ClassNotFoundException ;
    boolean delete(String id) throws SQLException, ClassNotFoundException ;
    String generateNewID() throws SQLException, ClassNotFoundException ;
    SalaryDto search(String id) throws SQLException, ClassNotFoundException ;
    List<EmployeeDto> getAllEmployee() throws SQLException, ClassNotFoundException;

    }
