package lk.ijse.animal_clinic.model;

import lk.ijse.animal_clinic.db.DbConnection;
import lk.ijse.animal_clinic.dto.DoctorDto;
import lk.ijse.animal_clinic.dto.customerDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorModel {

    public boolean saveDoctor(final  DoctorDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "Insert into doctor values(?,?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getTel());
        pstm.setString(4, dto.getEmail());
        pstm.setString(5, dto.getAddress());
        pstm.setTime(6, dto.getComeInTime());
        pstm.setTime(7, dto.getOutTime());

        boolean isSaved = pstm.executeUpdate()>0;
        return isSaved;
    }

    public boolean updateDoctor(final DoctorDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE doctor  SET  doctor_name = ? , tel = ? , e_mail=? , address = ? come_in_time=? com_out_time ?   WHERE doctor_id =?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getTel());
        pstm.setString(3, dto.getEmail());
        pstm.setString(4, dto.getAddress());
        pstm.setTime(5, dto.getComeInTime());
        pstm.setTime(6, dto.getOutTime());
        pstm.setString(7, dto.getId());
        return pstm.executeUpdate() > 0;

    }

    public boolean doctorDelete(String doctor_id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql =" delete from doctor where doctor_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,doctor_id);


        return pstm.executeUpdate()>0;
    }


    public DoctorDto searchDoctor(String doctor_id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql =" select * from doctor where doctor_id=?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, doctor_id);

        ResultSet resultSet = pstm.executeQuery();

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
    public static List<DoctorDto> getAllDoctor() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM doctor";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<DoctorDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String tel = resultSet.getString(3);
            String e_mail = resultSet.getString(4);
            String address = resultSet.getString(5);
            Time comeInTime = resultSet.getTime(6);
            Time outTime = resultSet.getTime(7);


            var dto = new DoctorDto(id, name, tel, e_mail ,address,comeInTime,outTime);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public int getDoctorCount() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT COUNT(*) FROM doctor";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = pstm.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } finally {
        }
        return 0;
    }

    public String generateNextDoctorId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT doctor_id FROM doctor ORDER BY doctor_id DESC LIMIT 1";
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
            String[] split = currentOrderId.split("D");
            int id = Integer.parseInt(split[1]);
            id++;

            String formattedId = String.format("%03d", id);

            return "D" + formattedId;
        }
        return "D001";
    }

    public DoctorDto loadTime(String doctorID) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql =" select * from doctor where doctor_id=?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, doctorID);

        ResultSet resultSet = pstm.executeQuery();

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
