package lk.ijse.animal_clinic.bo.custom;

import lk.ijse.animal_clinic.dto.StockDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StockBO {
    ArrayList<StockDto> getAll() throws SQLException, ClassNotFoundException ;
    boolean save(final StockDto dto) throws SQLException, ClassNotFoundException ;
    boolean update(final StockDto dto) throws SQLException, ClassNotFoundException ;

    boolean exist(String id) throws SQLException, ClassNotFoundException ;
    boolean delete(String id) throws SQLException, ClassNotFoundException ;
    String generateNewID() throws SQLException, ClassNotFoundException ;
    StockDto search(String id) throws SQLException, ClassNotFoundException ;
}
