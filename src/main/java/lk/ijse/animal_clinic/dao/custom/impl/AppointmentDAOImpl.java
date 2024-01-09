package lk.ijse.animal_clinic.dao.custom.impl;

import lk.ijse.animal_clinic.dao.SQLUtil;
import lk.ijse.animal_clinic.dao.custom.AppointmentDAO;
import lk.ijse.animal_clinic.db.DbConnection;
import lk.ijse.animal_clinic.dto.AppointmentDto;
import lk.ijse.animal_clinic.dto.customerDto;

import java.sql.*;
import java.util.ArrayList;

public class AppointmentDAOImpl implements AppointmentDAO {
    @Override
    public ArrayList<AppointmentDto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM appointements ");
        ArrayList<AppointmentDto> allAppointements = new ArrayList<>();

        while (rst.next()) {
            AppointmentDto entity = new AppointmentDto(
                    rst.getString("app_id"),
                    rst.getString("doctor_id"),
                    rst.getString("pet_id"),
                    rst.getDate("date"),
                    rst.getTime("time"),
                    rst.getInt("Number")
            );
            allAppointements.add(entity);
        }
        return allAppointements;
    }

    @Override
    public boolean save(AppointmentDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.
            execute("INSERT INTO appointements VALUES(?, ?, ?, ?,?,?)",
                    dto.getApp_id(), dto.getDoctor_id(),   dto.getPet_id(), dto.getDate(),  dto.getTime(), dto.getNumber());}


    @Override
    public boolean update(AppointmentDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.
                execute ("UPDATE appointements SET  doctor_id = ? , pet_id = ? , date=? , time= ?, N umber = ?  WHERE app_id =?",
                         dto.getDoctor_id(),   dto.getPet_id(), dto.getDate(),  dto.getTime(), dto.getNumber(),dto.getApp_id());}



    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT app_id FROM appointements WHERE app_id=?", id);
        return rst.next();
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM appointements WHERE app_id=?", id);

    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute( "SELECT app_id FROM appointements ORDER BY app_id DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("app_id");
            int newCustomerId = Integer.parseInt(id.replace("A00", "")) + 1;
            return String.format("A%03d", newCustomerId);
        } else {
            return "A001";
        }    }

    @Override
    public AppointmentDto search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM appointements WHERE app_id = ?", id);
        rst.next();
        return new AppointmentDto(id + "", rst.getString("doctor_id"), rst.getString("pet_id"), rst.getDate("date"), rst.getTime("time"),rst.getInt("Number"));
    }

    @Override
    public AppointmentDto loadNumber(String doctorID) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM appointements WHERE doctor_id = ? ORDER BY date DESC, time DESC LIMIT 1;";
        ResultSet resultSet = SQLUtil.execute(sql, doctorID);
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
