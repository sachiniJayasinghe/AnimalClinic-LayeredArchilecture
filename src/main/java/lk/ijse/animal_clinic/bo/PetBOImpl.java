package lk.ijse.animal_clinic.bo;

import lk.ijse.animal_clinic.bo.custom.PetBO;
import lk.ijse.animal_clinic.dao.DAOFactory;
import lk.ijse.animal_clinic.dao.custom.CustomerDAO;
import lk.ijse.animal_clinic.dao.custom.PetDAO;
import lk.ijse.animal_clinic.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.animal_clinic.dao.custom.impl.PetDAOImpl;
import lk.ijse.animal_clinic.dto.PetDto;
import lk.ijse.animal_clinic.dto.customerDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetBOImpl implements PetBO {
    PetDAO petDAO = (PetDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Pet);

    CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Customer);


    @Override
    public ArrayList<PetDto> getAll() throws SQLException, ClassNotFoundException {


        return petDAO.getAll();
    }

    @Override
    public boolean save(PetDto dto) throws SQLException, ClassNotFoundException {
        return petDAO.save(new PetDto(dto.getPet_id(), dto.getName(), dto.getPet_type(), dto.getAge(), dto.getCus_id()));
    }

    @Override
    public boolean update(PetDto dto) throws SQLException, ClassNotFoundException {
        return petDAO.update(new PetDto(dto.getPet_id(), dto.getName(), dto.getPet_type(), dto.getAge(), dto.getCus_id()));
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return petDAO.exist(id);
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return petDAO.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return petDAO.generateNewID();
    }

    @Override
    public PetDto search(String id) throws SQLException, ClassNotFoundException {
        return petDAO.search(id);
    }

    @Override
    public int getPetCount() throws SQLException, ClassNotFoundException {
        return petDAO.getPetCount();
    }

    @Override
    public List<customerDto> getAllCustomers() throws SQLException, ClassNotFoundException {
        return customerDAO.getAll();
    }
}
