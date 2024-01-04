package lk.ijse.animal_clinic.dao.custom;

import lk.ijse.animal_clinic.dao.CrudDAO;
import lk.ijse.animal_clinic.dto.DoctorDto;

import java.sql.SQLException;

public interface DoctorDAO extends CrudDAO<DoctorDto> {
     int getDoctorCount() throws SQLException, ClassNotFoundException;
      DoctorDto loadTime(String doctorID) throws SQLException, ClassNotFoundException ;

     }
