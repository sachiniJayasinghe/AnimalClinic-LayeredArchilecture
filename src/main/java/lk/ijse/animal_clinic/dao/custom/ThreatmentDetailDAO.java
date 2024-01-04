package lk.ijse.animal_clinic.dao.custom;

import lk.ijse.animal_clinic.dao.CrudDAO;
import lk.ijse.animal_clinic.dto.ThreatmentDetailsDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ThreatmentDetailDAO extends CrudDAO<ThreatmentDetailsDto> {
    public String getCustomerId(String value) throws SQLException, ClassNotFoundException ;
      List<ThreatmentDetailsDto> extractThreatmentDetailsDtoList(ResultSet resultSet) throws SQLException ;

    }
