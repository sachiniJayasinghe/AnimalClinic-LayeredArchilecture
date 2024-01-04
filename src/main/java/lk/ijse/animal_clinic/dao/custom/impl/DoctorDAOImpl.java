package lk.ijse.animal_clinic.dao.custom.impl;

import lk.ijse.animal_clinic.dao.SQLUtil;
import lk.ijse.animal_clinic.dao.custom.DoctorDAO;
import lk.ijse.animal_clinic.dto.DoctorDto;

import java.sql.*;
import java.util.ArrayList;

public class DoctorDAOImpl implements DoctorDAO {


    @Override
    public ArrayList<DoctorDto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM doctor");
        ArrayList<DoctorDto> allDoctor = new ArrayList<>();

        while (rst.next()) {
            DoctorDto entity = new DoctorDto(
                    rst.getString("id"),
                    rst.getString("name"),
                    rst.getString("tel"),
                    rst.getString("e_mail"),
                    rst.getString("address"),
                    rst.getTime("comeInTime"),
                    rst.getTime("outTime")
            );
            allDoctor.add(entity);
        }
        return allDoctor;
    }

    @Override
    public boolean save(DoctorDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.
                execute("INSERT INTO doctor VALUES(?, ?, ?, ?,?)",
                        dto.getId(), dto.getName(), dto.getTel(), dto.getEmail(), dto.getAddress(),dto.getComeInTime(),dto.getOutTime());
    }



    @Override
    public boolean update(DoctorDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE doctor  SET  doctor_name = ? , tel = ? , e_mail=? , address = ? come_in_time=? com_out_time ?   WHERE doctor_id =?",
                dto.getId(), dto.getName(), dto.getTel(), dto.getEmail(), dto.getAddress(),dto.getComeInTime(),dto.getOutTime());

    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM doctor WHERE id=?", id);
        return rst.next();
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM doctor WHERE id=?", id);

    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM doctor ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("D0-", "")) + 1;
            return String.format("D0-%03d", newCustomerId);
        } else {
            return "D001";
        }
    }
    @Override
    public DoctorDto search(String id) throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT * FROM doctor WHERE cus_id = ?", id);
        rst.next();
        return new DoctorDto(id + "", rst.getString("name"), rst.getString("tel"), rst.getString("e_mail"), rst.getString("address"),rst.getTime("comeInTime"),rst.getTime("outTime"));
    }

    @Override
    public int getDoctorCount() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM doctor";
        try {
            ResultSet resultSet = SQLUtil.execute(sql);
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }
        return 0;
    }
    @Override
    public DoctorDto loadTime(String doctorID) throws SQLException, ClassNotFoundException {
        String sql =" select * from doctor where doctor_id=?";

        ResultSet resultSet = SQLUtil.execute(sql,doctorID);

        DoctorDto dto = null;
        if(resultSet.next()){
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String tel = resultSet.getString(3);
            String e_mail = resultSet.getString(4);
            String address = resultSet.getString(5);
            Time comeInTime = resultSet.getTime(6);
            Time outTime = resultSet.getTime(7);

            dto = new DoctorDto(id, name, tel,e_mail,address,comeInTime,outTime);
        }
        return  dto;
    }
}
