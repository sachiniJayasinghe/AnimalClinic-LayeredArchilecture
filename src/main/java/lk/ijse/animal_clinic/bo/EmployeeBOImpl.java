package lk.ijse.animal_clinic.bo;

import lk.ijse.animal_clinic.bo.custom.EmployeeBO;
import lk.ijse.animal_clinic.dao.custom.EmployeeDAO;
import lk.ijse.animal_clinic.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.animal_clinic.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    @Override
    public ArrayList<EmployeeDto> getAll() throws SQLException, ClassNotFoundException {
        return employeeDAO.getAll();
    }

    @Override
    public boolean save(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.save( new EmployeeDto(dto.getEmp_id(), dto.getName(), dto.getTel(), dto.getEmail(), dto.getAddress()));

    }

    @Override
    public boolean update(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update( new EmployeeDto(dto.getEmp_id(), dto.getName(), dto.getTel(), dto.getEmail(), dto.getAddress()));

    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.exist(id);
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return employeeDAO.generateNewID();
    }

    @Override
    public EmployeeDto search(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.search(id);
    }
}
