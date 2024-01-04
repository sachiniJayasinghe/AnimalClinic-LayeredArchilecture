package lk.ijse.animal_clinic.bo.custom;

import lk.ijse.animal_clinic.dto.SupliyerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupliyerBO {
    ArrayList<SupliyerDto> getAll() throws SQLException, ClassNotFoundException ;
    boolean save(final SupliyerDto dto) throws SQLException, ClassNotFoundException ;
    boolean update(final SupliyerDto dto) throws SQLException, ClassNotFoundException ;

    boolean exist(String id) throws SQLException, ClassNotFoundException ;
    boolean delete(String id) throws SQLException, ClassNotFoundException ;
    String generateNewID() throws SQLException, ClassNotFoundException ;
    SupliyerDto search(String id) throws SQLException, ClassNotFoundException ;
}
