package lk.ijse.animal_clinic.bo.custom;

import lk.ijse.animal_clinic.bo.SuperBO;
import lk.ijse.animal_clinic.dto.AppointmentDto;
import lk.ijse.animal_clinic.dto.DoctorDto;
import lk.ijse.animal_clinic.dto.PetDto;
import lk.ijse.animal_clinic.dto.customerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AppointmentBO extends SuperBO {
    ArrayList<AppointmentDto> getAll() throws SQLException, ClassNotFoundException ;

    boolean save(final AppointmentDto dto) throws SQLException, ClassNotFoundException ;
    boolean update(final AppointmentDto dto) throws SQLException, ClassNotFoundException ;

    boolean exist(String id) throws SQLException, ClassNotFoundException ;
    boolean delete(String id) throws SQLException, ClassNotFoundException ;
    String generateNewID() throws SQLException, ClassNotFoundException ;
    AppointmentDto search(String id) throws SQLException, ClassNotFoundException ;
     ArrayList<PetDto> getAllPets() throws SQLException, ClassNotFoundException ;
     ArrayList<DoctorDto> getAllDoctor() throws SQLException, ClassNotFoundException ;

     AppointmentDto loadNumber(String doctorID) throws SQLException, ClassNotFoundException ;
     DoctorDto loadTime(String doctorID) throws SQLException, ClassNotFoundException;
     customerDto loadAll(String appId) throws SQLException, ClassNotFoundException;

    }
//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