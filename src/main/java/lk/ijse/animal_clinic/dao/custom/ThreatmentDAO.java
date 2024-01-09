package lk.ijse.animal_clinic.dao.custom;

import lk.ijse.animal_clinic.dao.CrudDAO;
import lk.ijse.animal_clinic.dto.TreatementDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ThreatmentDAO extends CrudDAO<TreatementDto> {
     double getCost(String value) throws SQLException, ClassNotFoundException ;
    String getThreatmentId(String value) throws SQLException, ClassNotFoundException ;

     TreatementDto extractTreatementDto(ResultSet resultSet) throws SQLException ;
    List<TreatementDto> extractTreatementDtoList(ResultSet resultSet) throws SQLException ;



    }
