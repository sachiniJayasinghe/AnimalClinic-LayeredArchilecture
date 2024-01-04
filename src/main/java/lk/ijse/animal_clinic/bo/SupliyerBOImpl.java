package lk.ijse.animal_clinic.bo;

import lk.ijse.animal_clinic.bo.custom.SupliyerBO;
import lk.ijse.animal_clinic.dao.custom.SupliyerDAO;
import lk.ijse.animal_clinic.dao.custom.impl.SupliyerDAOImpl;
import lk.ijse.animal_clinic.dto.SupliyerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupliyerBOImpl implements SupliyerBO {
    SupliyerDAO supliyerDAO = new SupliyerDAOImpl();
    @Override
    public ArrayList<SupliyerDto> getAll() throws SQLException, ClassNotFoundException {
        return supliyerDAO.getAll() ;
    }

    @Override
    public boolean save(SupliyerDto dto) throws SQLException, ClassNotFoundException {
        return supliyerDAO.save(new SupliyerDto(dto.getSupliyer_id(), dto.getStock_id(), dto.getSupliyer_name(),
                dto.getTel(), dto.getE_mail(), dto.getAddress()));
    }

    @Override
    public boolean update(SupliyerDto dto) throws SQLException, ClassNotFoundException {
        return supliyerDAO.update(new SupliyerDto(dto.getSupliyer_id(), dto.getStock_id(), dto.getSupliyer_name(),
                dto.getTel(), dto.getE_mail(), dto.getAddress()));    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return  supliyerDAO.exist(id);
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return  supliyerDAO.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return supliyerDAO.generateNewID();
    }

    @Override
    public SupliyerDto search(String id) throws SQLException, ClassNotFoundException {
        return supliyerDAO.search(id);
    }
}
