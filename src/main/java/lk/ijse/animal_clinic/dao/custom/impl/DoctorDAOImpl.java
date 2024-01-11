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
                    rst.getString("doctor_id"),
                    rst.getString("doctor_name"),
                    rst.getString("tel"),
                    rst.getString("address"),
                    rst.getString("e_mail"),
                    rst.getTime("come_in_time"),
                    rst.getTime("com_out_time")
            );
            allDoctor.add(entity);
        }
        return allDoctor;
    }

    @Override
    public boolean save(DoctorDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.
                execute("INSERT INTO doctor VALUES(?,?,?,?,?,?,?)",
                        dto.getId(), dto.getName(), dto.getTel(),  dto.getAddress(), dto.getEmail(),dto.getComeInTime(),dto.getOutTime());
    }



    @Override
    public boolean update(DoctorDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE doctor SET doctor_name = ?, tel = ?, address = ?, e_mail = ?, come_in_time = ?, com_out_time = ? WHERE doctor_id = ?",
                dto.getName(), dto.getTel(),  dto.getAddress(),  dto.getEmail(),dto.getComeInTime(), dto.getOutTime(), dto.getId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT doctor_id FROM doctor WHERE doctor_id=?", id);
        return rst.next();
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM doctor WHERE doctor_id=?", id);

    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT doctor_id FROM doctor ORDER BY doctor_id DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("doctor_id");
            int newCustomerId = Integer.parseInt(id.replace("D00", "")) + 1;
            return String.format("D%03d", newCustomerId);
        } else {
            return "D001";
        }
    }
    @Override
    public DoctorDto search(String id) throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT * FROM doctor WHERE doctor_id= ?", id);
        rst.next();
        return new DoctorDto(id + "", rst.getString("doctor_name"), rst.getString("tel"), rst.getString("address"),rst.getString("e_mail") ,rst.getTime("come_in_time"),rst.getTime("com_out_time"));
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
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public DoctorDto loadTime(String doctorID) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM doctor WHERE doctor_id=?";
        System.out.println("doctorID: " + doctorID);

        Object result = SQLUtil.execute(sql, doctorID);
        DoctorDto dto = null;

        if (result instanceof ResultSet) {
            ResultSet resultSet = (ResultSet) result;
            if (resultSet.next()) {
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String tel = resultSet.getString(3);
                String address = resultSet.getString(4);
                String e_mail = resultSet.getString(5);
                Time comeInTime = resultSet.getTime(6);
                Time outTime = resultSet.getTime(7);

                dto = new DoctorDto(id, name, tel, address, e_mail, comeInTime, outTime);
            }
        }
        return dto;
    }

}
