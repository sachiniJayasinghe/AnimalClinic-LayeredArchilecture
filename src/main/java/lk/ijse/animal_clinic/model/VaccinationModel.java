package lk.ijse.animal_clinic.model;

import lk.ijse.animal_clinic.db.DbConnection;
import lk.ijse.animal_clinic.dto.AppointmentDto;
import lk.ijse.animal_clinic.dto.vaccinationsDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VaccinationModel {
    public boolean saveVaccination(final vaccinationsDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO vaccinations VALUES(?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getVaccination_id());
        pstm.setString(2, dto.getVaccinationName());
        pstm.setDate(3, dto.getNext_dueDate());



        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

    public boolean updateVaccination(final vaccinationsDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE vaccinations SET  vaccine_name = ? , next_dueDate= ?  WHERE vaccination_id =?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getVaccinationName());
        pstm.setDate(2, dto.getNext_dueDate());
        pstm.setString(3, dto.getVaccination_id());




        return pstm.executeUpdate() > 0;


    }

    public boolean VaccinationModel( final String vaccinationId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM vaccinations WHERE vaccination_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, vaccinationId);

        return pstm.executeUpdate() > 0;
    }

    public vaccinationsDto searchVaccination(final String vaccinationId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM vaccinations WHERE vaccination_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, vaccinationId);

        ResultSet resultSet = pstm.executeQuery();

        vaccinationsDto dto = null;

        if(resultSet.next()) {
            String vaccination_id = resultSet.getString(1);
            String vaccine_name = resultSet.getString(2);
            Date next_dueDate = Date.valueOf(resultSet.getString(3));



            dto = new vaccinationsDto(vaccination_id, vaccine_name, next_dueDate);
        }

        return dto;

    }

    public List<vaccinationsDto> getAllVaccinatoin() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM vaccinations";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<vaccinationsDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String vaccination_id = resultSet.getString(1);
            String vaccine_name = resultSet.getString(2);
            Date next_dueDate = Date.valueOf(resultSet.getString(3));

          var  dto = new vaccinationsDto(vaccination_id, vaccine_name, next_dueDate);



            dtoList.add(dto);
        }
        return dtoList;
    }
    }



