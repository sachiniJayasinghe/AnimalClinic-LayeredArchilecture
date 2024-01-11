package lk.ijse.animal_clinic.bo;

import lk.ijse.animal_clinic.bo.custom.AppointmentBO;
import lk.ijse.animal_clinic.bo.custom.DoctorBO;
import lk.ijse.animal_clinic.dao.DAOFactory;
import lk.ijse.animal_clinic.dao.custom.*;
import lk.ijse.animal_clinic.dao.custom.impl.AppointmentDAOImpl;
import lk.ijse.animal_clinic.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.animal_clinic.dao.custom.impl.DoctorDAOImpl;
import lk.ijse.animal_clinic.dao.custom.impl.PetDAOImpl;
import lk.ijse.animal_clinic.dto.AppointmentDto;
import lk.ijse.animal_clinic.dto.DoctorDto;
import lk.ijse.animal_clinic.dto.PetDto;
import lk.ijse.animal_clinic.dto.customerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class AppointementBOImpl implements AppointmentBO {

    PetDAO petDAO = (PetDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Pet);
    CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Customer);
    DoctorDAO doctorDAO =  (DoctorDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Doctor);
    AppointmentDAO appointmentDAO = (AppointmentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Appointement);

    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Query);
    @Override
    public ArrayList<AppointmentDto> getAll() throws SQLException, ClassNotFoundException {
        return appointmentDAO.getAll();
    }

    @Override
    public boolean save(AppointmentDto dto) throws SQLException, ClassNotFoundException {
        return appointmentDAO.save(new AppointmentDto(dto.getApp_id(), dto.getDoctor_id(), dto.getPet_id(), dto.getDate(), dto.getTime(), dto.getNumber()));
    }

    @Override
    public boolean update(AppointmentDto dto) throws SQLException, ClassNotFoundException {
        return appointmentDAO.update(new AppointmentDto(dto.getApp_id(), dto.getDoctor_id(), dto.getPet_id(), dto.getDate(), dto.getTime(), dto.getNumber()));
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return appointmentDAO.exist(id);
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return appointmentDAO.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return appointmentDAO.generateNewID();
    }

    @Override
    public AppointmentDto search(String id) throws SQLException, ClassNotFoundException {
        return appointmentDAO.search(id);
    }

    @Override
    public ArrayList<PetDto> getAllPets() throws SQLException, ClassNotFoundException {
        return petDAO.getAll();
    }

    @Override
    public ArrayList<DoctorDto> getAllDoctor() throws SQLException, ClassNotFoundException {
        return doctorDAO.getAll();
    }

    @Override
    public AppointmentDto loadNumber(String doctorID) throws SQLException, ClassNotFoundException {
        return appointmentDAO.loadNumber(doctorID);
    }

    @Override
    public DoctorDto loadTime(String doctorID) throws SQLException, ClassNotFoundException {
        return doctorDAO.loadTime(doctorID);

    }
    @Override
    public customerDto loadAll(String appId) throws SQLException, ClassNotFoundException {
        return queryDAO.loadAll(appId);
    }
}