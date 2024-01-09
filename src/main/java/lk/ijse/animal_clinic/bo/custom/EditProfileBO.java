package lk.ijse.animal_clinic.bo.custom;

import lk.ijse.animal_clinic.bo.SuperBO;
import lk.ijse.animal_clinic.dto.UserDto;

import java.sql.SQLException;

public interface EditProfileBO extends SuperBO {
    public byte[] loadImg(String userID) throws SQLException, ClassNotFoundException;
    public boolean updateUser(UserDto dto) throws SQLException, ClassNotFoundException;



    }
