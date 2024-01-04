package lk.ijse.animal_clinic.bo;

import lk.ijse.animal_clinic.bo.custom.DoctorBO;
import lk.ijse.animal_clinic.dao.custom.DoctorDAO;
import lk.ijse.animal_clinic.dao.custom.impl.DoctorDAOImpl;
import lk.ijse.animal_clinic.dto.DoctorDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class DoctorBOImpl implements DoctorBO {

    DoctorDAO doctorDAO = new DoctorDAOImpl();
    @Override
    public ArrayList<DoctorDto> getAll() throws SQLException, ClassNotFoundException {
        return doctorDAO.getAll();
    }

    @Override
    public boolean save(DoctorDto dto) throws SQLException, ClassNotFoundException {
        return doctorDAO.save( new DoctorDto(  dto.getId(), dto.getName(), dto.getTel(), dto.getEmail(), dto.getAddress(),dto.getComeInTime(),dto.getOutTime()));
    }

    @Override
    public boolean update(DoctorDto dto) throws SQLException, ClassNotFoundException {
        return doctorDAO.update( new DoctorDto(  dto.getId(), dto.getName(), dto.getTel(), dto.getEmail(), dto.getAddress(),dto.getComeInTime(),dto.getOutTime()));

    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return doctorDAO.exist(id);
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return doctorDAO.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return doctorDAO.generateNewID();
    }

    @Override
    public DoctorDto search(String id) throws SQLException, ClassNotFoundException {
        return doctorDAO.search(id);
    }

    @Override
    public int getDoctorCount() throws ClassNotFoundException, SQLException {
        return getDoctorCount();
    }
}
