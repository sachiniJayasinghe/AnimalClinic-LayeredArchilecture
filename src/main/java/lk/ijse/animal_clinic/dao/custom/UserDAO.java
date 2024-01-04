package lk.ijse.animal_clinic.dao.custom;

import lk.ijse.animal_clinic.dao.CrudDAO;
import lk.ijse.animal_clinic.dto.UserDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserDAO extends CrudDAO<UserDto> {


  boolean  updatePassword(String text, String name) throws SQLException, ClassNotFoundException ;
     UserDto extractUserDto(ResultSet resultSet) throws SQLException ;
     byte[] loadImg(String userID) throws SQLException, ClassNotFoundException ;


    }
