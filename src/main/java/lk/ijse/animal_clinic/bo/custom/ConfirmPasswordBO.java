package lk.ijse.animal_clinic.bo.custom;

import lk.ijse.animal_clinic.bo.SuperBO;
import lk.ijse.animal_clinic.dto.UserDto;

import java.sql.SQLException;

public interface ConfirmPasswordBO extends SuperBO {
    public boolean save(UserDto dto) throws SQLException, ClassNotFoundException;

    }
