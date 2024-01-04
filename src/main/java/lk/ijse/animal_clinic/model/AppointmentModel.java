package lk.ijse.animal_clinic.model;

import lk.ijse.animal_clinic.db.DbConnection;
import lk.ijse.animal_clinic.dto.AppointmentDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentModel {

    public boolean saveAppointment(final AppointmentDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO appointements VALUES(?, ?, ?, ?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getApp_id());
        pstm.setString(2, dto.getDoctor_id());
        pstm.setString(3, dto.getPet_id());
        pstm.setDate(4, dto.getDate());
        pstm.setTime(5, dto.getTime());
        pstm.setInt(6, dto.getNumber());


        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

    public boolean updateAppointment(final AppointmentDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE appointements SET  doctor_id = ? , pet_id = ? , date=? , time= ? , Number = ?  WHERE app_id =?";
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setString(1, dto.getDoctor_id());
        pstm.setString(2, dto.getPet_id());
        pstm.setDate(3, dto.getDate());
        pstm.setTime(4, dto.getTime());
        pstm.setInt(5, dto.getNumber());
        pstm.setString(6, dto.getApp_id());


        return pstm.executeUpdate() > 0;


    }

    public boolean deleteAppointment(final String appId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM appointements WHERE app_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, appId);

        return pstm.executeUpdate() > 0;
    }

    public static AppointmentDto searchAppointment(String appId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM appointements WHERE app_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, appId);

        ResultSet resultSet = pstm.executeQuery();

        AppointmentDto dto = null;

        if(resultSet.next()) {
            String app_id = resultSet.getString(1);
            String doctor_id = resultSet.getString(2);
            String pet_id = resultSet.getString(3);
            Date date = Date.valueOf(resultSet.getString(4));
            Time time = Time.valueOf(resultSet.getString(5));
            int Number = resultSet.getInt(6);



            dto = new AppointmentDto(app_id, doctor_id, pet_id,date,time,Number);
        }

        return dto;

    }

    public static List<AppointmentDto > getAllAppointment() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM appointements";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<AppointmentDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String app_id = resultSet.getString(1);
            String doctor_id = resultSet.getString(2);
            String pet_id = resultSet.getString(3);
            Date date = Date.valueOf(resultSet.getString(4));
            Time time = Time.valueOf(resultSet.getString(5));
            int Number = resultSet.getInt(6);


            var dto = new AppointmentDto(app_id, doctor_id, pet_id,date,time, Number);

            dtoList.add(dto);
        }
        return dtoList;
    }

    public String generateNextOrderId() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT app_id FROM appointements ORDER BY app_id DESC LIMIT 1";
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
            String[] split = currentOrderId.split("A");
            int id = Integer.parseInt(split[1]);
            id++;

            String formattedId = String.format("%03d", id);

            return "A" + formattedId;
        }
        return "A001";
    }

    public AppointmentDto loadNumber(String doctorID) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM appointements WHERE doctor_id = ? ORDER BY date DESC, time DESC LIMIT 1;";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, doctorID);

        ResultSet resultSet = pstm.executeQuery();
        AppointmentDto dto = null;
        while (resultSet.next()) {
            String app_id = resultSet.getString(1);
            String doctor_id = resultSet.getString(2);
            String pet_id = resultSet.getString(3);
            Date date = Date.valueOf(resultSet.getString(4));
            Time time = Time.valueOf(resultSet.getString(5));
            int Number = resultSet.getInt(6);
            dto = new AppointmentDto(app_id, doctor_id, pet_id,date,time, Number);

        }
        return dto;
    }
}

