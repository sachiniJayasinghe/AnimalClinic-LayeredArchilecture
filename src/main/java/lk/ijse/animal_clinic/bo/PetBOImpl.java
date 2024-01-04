package lk.ijse.animal_clinic.bo;

import lk.ijse.animal_clinic.bo.custom.PetBO;
import lk.ijse.animal_clinic.dao.custom.PetDAO;
import lk.ijse.animal_clinic.dao.custom.impl.PetDAOImpl;
import lk.ijse.animal_clinic.dto.PetDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class PetBOImpl implements PetBO {
    PetDAO petDAO = new PetDAOImpl();


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
        return generateNewID();
    }

    @Override
    public PetDto search(String id) throws SQLException, ClassNotFoundException {
        return petDAO.search(id);
    }

    @Override
    public int getPetCount() throws SQLException, ClassNotFoundException {
        return getPetCount();
    }
}
