package lk.ijse.animal_clinic.dao;

import lk.ijse.animal_clinic.dto.customerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> {
    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    boolean save(T dto) throws SQLException, ClassNotFoundException ;
    boolean update(T dto) throws SQLException, ClassNotFoundException ;
    boolean exist(String id) throws SQLException, ClassNotFoundException ;
    boolean delete(String id) throws SQLException, ClassNotFoundException ;
    String generateNewID() throws SQLException, ClassNotFoundException ;
    public T search(String id) throws SQLException, ClassNotFoundException;

}
