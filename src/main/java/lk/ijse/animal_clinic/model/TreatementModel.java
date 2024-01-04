package lk.ijse.animal_clinic.model;

import lk.ijse.animal_clinic.db.DbConnection;
import lk.ijse.animal_clinic.dto.EmployeeDto;
import lk.ijse.animal_clinic.dto.TreatementDto;
import lk.ijse.animal_clinic.dto.customerDto;
import lk.ijse.animal_clinic.dto.vaccinationsDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TreatementModel {

    public boolean saveTreatement(final TreatementDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO treatement VALUES(?, ?, ?, ?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setString(1, dto.getTreatement_id());
        pstm.setString(2, dto.getVaccination_id());
        pstm.setString(3, dto.getType());
        pstm.setDouble(4, dto.getCost());
        pstm.setDate(5, dto.getDate());




        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

    public boolean updateTreatement(final TreatementDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE treatement SET  vaccination_id = ? , type= ? ,cost=?,date=? WHERE treatement_id =?";
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setString(1, dto.getVaccination_id());
        pstm.setString(2, dto.getType());
        pstm.setDouble(3, dto.getCost());
        pstm.setDate(4, dto.getDate());
        pstm.setString(5, dto.getTreatement_id());


        return pstm.executeUpdate() > 0;


    }

    public boolean TreatementModel(String treatementId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM treatement WHERE treatement_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, treatementId);

        return pstm.executeUpdate() > 0;
    }

    public TreatementDto searchTreatment(String treatementId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM treatement WHERE treatement_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, treatementId);

        ResultSet resultSet = pstm.executeQuery();

        TreatementDto dto = null;

        if(resultSet.next()) {
            String treatement_id = resultSet.getString(1);
            String vaccination_id = resultSet.getString(2);
            String type = resultSet.getString(3);
            double cost = resultSet.getDouble(4);
            Date date = Date.valueOf(resultSet.getString(5));



            dto = new TreatementDto(treatement_id,vaccination_id, type, cost,date);
        }

        return dto;

    }

    public static List<TreatementDto> getAllTreatement() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();


        String sql = "SELECT * FROM treatement";
        PreparedStatement pstm = connection.prepareStatement(sql);


        List<TreatementDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String treatement_id = resultSet.getString(1);
            String vaccination_id = resultSet.getString(2);
            String type = resultSet.getString(3);
            double cost = resultSet.getDouble(4);
            Date date = Date.valueOf(resultSet.getString(5));


            var dto = new TreatementDto(treatement_id,vaccination_id, type, cost,date);

            dtoList.add(dto);
        }
        return dtoList;
    }

    public double getCost(String value) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        List<TreatementDto> dtoList = new ArrayList<>();

        String sql = "SELECT * from treatement WHERE type = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, value);

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String treatement_id = resultSet.getString(1);
            String vaccination_id = resultSet.getString(2);
            String type = resultSet.getString(3);
            double cost = resultSet.getDouble(4);
            Date date = Date.valueOf(resultSet.getString(5));


            var dto = new TreatementDto(treatement_id,vaccination_id, type, cost,date);

            dtoList.add(dto);
        }
        if (!dtoList.isEmpty()) {
            return dtoList.get(0).getCost();
        } else {
            return 0.0;
        }    }

    public String getThreatmentId(String value) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        List<TreatementDto> dtoList = new ArrayList<>();

        String sql = "SELECT * from treatement WHERE type = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, value);

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String treatement_id = resultSet.getString(1);
            String vaccination_id = resultSet.getString(2);
            String type = resultSet.getString(3);
            double cost = resultSet.getDouble(4);
            Date date = Date.valueOf(resultSet.getString(5));


            var dto = new TreatementDto(treatement_id,vaccination_id, type, cost,date);

            dtoList.add(dto);
        }
        if (!dtoList.isEmpty()) {
            return dtoList.get(0).getTreatement_id();
        } else {
            return null;
        }
    }

    public String generateNextThreatmentId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT treatement_id FROM treatement ORDER BY treatement_id DESC LIMIT 1";
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        String currentOrderId = null;

        if (resultSet.next()) {
            currentOrderId = resultSet.getString(1);
            return splitOrderId(currentOrderId);
        }
        return splitOrderId(null);
    }

    private String splitOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("T");
            int id = Integer.parseInt(split[1]);
            id++;

            String formattedId = String.format("%03d", id);

            return "T" + formattedId;
        }
        return "T001";
    }
}