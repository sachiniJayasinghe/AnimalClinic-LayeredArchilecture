package lk.ijse.animal_clinic.bo.custom;

import javafx.event.ActionEvent;
import lk.ijse.animal_clinic.bo.SuperBO;
import lk.ijse.animal_clinic.dto.EmployeeDto;
import lk.ijse.animal_clinic.dto.UserDto;

import java.sql.SQLException;

public interface ChangePasswordBO extends SuperBO {

    boolean updatePassword(String text, String name) throws SQLException, ClassNotFoundException;

    }
