package lk.ijse.animal_clinic.bo.custom;

import lk.ijse.animal_clinic.dto.vaccinationsDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface vaccinationsBO {
    ArrayList<vaccinationsDto> getAll() throws SQLException, ClassNotFoundException ;
    boolean save(final vaccinationsDto dto) throws SQLException, ClassNotFoundException ;
    boolean update(final vaccinationsDto dto) throws SQLException, ClassNotFoundException ;

    boolean exist(String id) throws SQLException, ClassNotFoundException ;
    boolean delete(String id) throws SQLException, ClassNotFoundException ;
    String generateNewID() throws SQLException, ClassNotFoundException ;
    vaccinationsDto search(String id) throws SQLException, ClassNotFoundException ;
}
