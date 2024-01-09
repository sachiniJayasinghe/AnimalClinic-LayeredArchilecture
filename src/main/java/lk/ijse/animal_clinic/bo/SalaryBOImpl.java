package lk.ijse.animal_clinic.bo;

import lk.ijse.animal_clinic.bo.custom.SalaryBO;
import lk.ijse.animal_clinic.dao.DAOFactory;
import lk.ijse.animal_clinic.dao.custom.EmployeeDAO;
import lk.ijse.animal_clinic.dao.custom.SalaryDAO;
import lk.ijse.animal_clinic.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.animal_clinic.dao.custom.impl.SalaryDAOImpl;
import lk.ijse.animal_clinic.dto.EmployeeDto;
import lk.ijse.animal_clinic.dto.SalaryDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryBOImpl implements SalaryBO {
    SalaryDAO salaryDAO =  (SalaryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Salary);

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Employee);
    @Override
    public ArrayList<SalaryDto> getAll() throws SQLException, ClassNotFoundException {
        return salaryDAO.getAll();
    }

    @Override
    public boolean save(SalaryDto dto) throws SQLException, ClassNotFoundException {
        return salaryDAO.save( new SalaryDto( dto.getSalary_id(), dto.getEmp_id(), dto.getDate(), dto.getSalary_month(), dto.getAmount()));
    }

    @Override
    public boolean update(SalaryDto dto) throws SQLException, ClassNotFoundException {
        return salaryDAO.update( new SalaryDto( dto.getSalary_id(), dto.getEmp_id(), dto.getDate(), dto.getSalary_month(), dto.getAmount()));
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return salaryDAO.exist(id);
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return salaryDAO.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return salaryDAO.generateNewID();
    }

    @Override
    public SalaryDto search(String id) throws SQLException, ClassNotFoundException {
        return salaryDAO.search(id);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() throws SQLException, ClassNotFoundException {
        return employeeDAO.getAll();
    }
}
