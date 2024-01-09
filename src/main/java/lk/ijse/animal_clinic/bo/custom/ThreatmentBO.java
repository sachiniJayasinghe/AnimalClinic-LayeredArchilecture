package lk.ijse.animal_clinic.bo.custom;

import lk.ijse.animal_clinic.bo.SuperBO;
import lk.ijse.animal_clinic.dto.TreatementDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ThreatmentBO extends SuperBO {
     boolean save(final TreatementDto dto) throws SQLException, ClassNotFoundException ;

     boolean update(final TreatementDto dto) throws SQLException, ClassNotFoundException ;
     boolean exist(String id) throws SQLException, ClassNotFoundException ;
     boolean delete(String treatementId) throws SQLException, ClassNotFoundException ;
     String generateNewID() throws SQLException, ClassNotFoundException ;

    TreatementDto search(String id) throws SQLException, ClassNotFoundException ;
     ArrayList<TreatementDto> getAll() throws SQLException, ClassNotFoundException ;
     double getCost(String value) throws SQLException, ClassNotFoundException ;
     String getThreatmentId(String value) throws SQLException, ClassNotFoundException ;

     TreatementDto extractTreatementDto(ResultSet resultSet) throws SQLException ;

     List<TreatementDto> extractTreatementDtoList(ResultSet resultSet) throws SQLException ;

}
