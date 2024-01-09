package lk.ijse.animal_clinic.bo.custom;

import lk.ijse.animal_clinic.bo.SuperBO;
import lk.ijse.animal_clinic.dto.PetDto;
import lk.ijse.animal_clinic.dto.customerDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PetBO extends SuperBO {
    ArrayList<PetDto> getAll() throws SQLException, ClassNotFoundException ;

    boolean save(final PetDto dto) throws SQLException, ClassNotFoundException ;
    boolean update(final PetDto dto) throws SQLException, ClassNotFoundException ;

    boolean exist(String id) throws SQLException, ClassNotFoundException ;
    boolean delete(String id) throws SQLException, ClassNotFoundException ;
    String generateNewID() throws SQLException, ClassNotFoundException ;
    PetDto search(String id) throws SQLException, ClassNotFoundException ;
    int getPetCount() throws SQLException, ClassNotFoundException ;
     List<customerDto> getAllCustomers() throws SQLException, ClassNotFoundException;


    }
