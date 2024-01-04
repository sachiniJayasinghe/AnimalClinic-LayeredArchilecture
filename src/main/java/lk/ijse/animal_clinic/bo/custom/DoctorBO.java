package lk.ijse.animal_clinic.bo.custom;

import lk.ijse.animal_clinic.dto.DoctorDto;
import lk.ijse.animal_clinic.dto.customerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DoctorBO {
    ArrayList<DoctorDto> getAll() throws SQLException, ClassNotFoundException ;

    boolean save(final DoctorDto dto) throws SQLException, ClassNotFoundException ;
    boolean update(final DoctorDto dto) throws SQLException, ClassNotFoundException ;

    boolean exist(String id) throws SQLException, ClassNotFoundException ;
    boolean delete(String id) throws SQLException, ClassNotFoundException ;
    String generateNewID() throws SQLException, ClassNotFoundException ;
    DoctorDto search(String id) throws SQLException, ClassNotFoundException ;
    int getDoctorCount() throws ClassNotFoundException, SQLException;
}
