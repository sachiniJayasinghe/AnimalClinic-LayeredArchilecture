package lk.ijse.animal_clinic.bo.custom;

import lk.ijse.animal_clinic.bo.SuperBO;
import lk.ijse.animal_clinic.dto.StockDto;
import lk.ijse.animal_clinic.dto.SupliyerDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupliyerBO extends SuperBO {
    ArrayList<SupliyerDto> getAll() throws SQLException, ClassNotFoundException ;
    boolean save(final SupliyerDto dto) throws SQLException, ClassNotFoundException ;
    boolean update(final SupliyerDto dto) throws SQLException, ClassNotFoundException ;

    boolean exist(String id) throws SQLException, ClassNotFoundException ;
    boolean delete(String id) throws SQLException, ClassNotFoundException ;
    String generateNewID() throws SQLException, ClassNotFoundException ;
    SupliyerDto search(String id) throws SQLException, ClassNotFoundException ;
    List<StockDto> getAllStock() throws SQLException, ClassNotFoundException;
}
