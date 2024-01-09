package lk.ijse.animal_clinic.bo.custom;

import lk.ijse.animal_clinic.bo.SuperBO;
import lk.ijse.animal_clinic.dto.UserDto;

import java.sql.SQLException;

public interface LoginFormBO extends SuperBO {
     UserDto search(String user) throws SQLException, ClassNotFoundException;
}
