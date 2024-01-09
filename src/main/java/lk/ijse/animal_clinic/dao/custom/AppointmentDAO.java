package lk.ijse.animal_clinic.dao.custom;

import lk.ijse.animal_clinic.dao.CrudDAO;
import lk.ijse.animal_clinic.dto.AppointmentDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AppointmentDAO extends CrudDAO<AppointmentDto> {
     AppointmentDto loadNumber(String doctorID) throws SQLException, ClassNotFoundException;

}
