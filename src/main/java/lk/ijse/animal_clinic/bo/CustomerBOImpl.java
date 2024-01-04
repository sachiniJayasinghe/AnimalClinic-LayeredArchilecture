package lk.ijse.animal_clinic.bo;

import lk.ijse.animal_clinic.bo.custom.CustomerBO;
import lk.ijse.animal_clinic.dao.custom.CustomerDAO;
import lk.ijse.animal_clinic.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.animal_clinic.dto.customerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = new CustomerDAOImpl();
    @Override
    public ArrayList<customerDto> getAll() throws SQLException, ClassNotFoundException {
        return customerDAO.getAll();
    }

    @Override
    public boolean save(customerDto dto) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new customerDto( dto.getId(), dto.getName(), dto.getTel(), dto.getEmail(), dto.getAddress()));
    }

    @Override
    public boolean update(customerDto dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new customerDto(dto.getId(), dto.getName(), dto.getTel(), dto.getEmail(), dto.getAddress()));
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
     return customerDAO.exist(id);
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNewID();
    }

    @Override
    public customerDto search(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.search(id);
    }

    @Override
    public int getCustomerCount() throws ClassNotFoundException, SQLException {
        return customerDAO.getCustomerCount();
    }
}
