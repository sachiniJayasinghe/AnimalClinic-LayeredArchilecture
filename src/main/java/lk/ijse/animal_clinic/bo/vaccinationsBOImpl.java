package lk.ijse.animal_clinic.bo;

import lk.ijse.animal_clinic.bo.custom.vaccinationsBO;
import lk.ijse.animal_clinic.dao.custom.VaccinationsDAO;
import lk.ijse.animal_clinic.dao.custom.impl.vaccinationsDAOImpl;
import lk.ijse.animal_clinic.dto.vaccinationsDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class vaccinationsBOImpl implements vaccinationsBO {
    VaccinationsDAO vaccinationsDAO = new vaccinationsDAOImpl();
    @Override
    public ArrayList<vaccinationsDto> getAll() throws SQLException, ClassNotFoundException {
        return vaccinationsDAO.getAll();
    }

    @Override
    public boolean save(vaccinationsDto dto) throws SQLException, ClassNotFoundException {
        return vaccinationsDAO.save( new vaccinationsDto(dto.getVaccination_id(), dto.getVaccinationName(), dto.getNext_dueDate()));
    }

    @Override
    public boolean update(vaccinationsDto dto) throws SQLException, ClassNotFoundException {
        return vaccinationsDAO.update( new vaccinationsDto(dto.getVaccination_id(), dto.getVaccinationName(), dto.getNext_dueDate()));
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return vaccinationsDAO.exist(id);
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return vaccinationsDAO.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return vaccinationsDAO.generateNewID();
    }

    @Override
    public vaccinationsDto search(String id) throws SQLException, ClassNotFoundException {
        return vaccinationsDAO.search(id);
    }
}
