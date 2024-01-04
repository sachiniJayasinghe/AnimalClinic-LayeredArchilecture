package lk.ijse.animal_clinic.model;

import lk.ijse.animal_clinic.db.DbConnection;
import lk.ijse.animal_clinic.dto.TreatementDto;
import lk.ijse.animal_clinic.dto.UserDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class UserModel {
    public boolean save(UserDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "Insert into user values(?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,dto.getId());
        pstm.setString(2, dto.getPassword());
        pstm.setString(3, dto.getName());
        pstm.setString(4, dto.getAddress());
        pstm.setString(5, dto.getEmail());
        pstm.setBytes(6, dto.getImage());

        boolean isSaved = pstm.executeUpdate()>0;
        return isSaved;

    }


    public UserDto search(String user) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        List<UserDto> dtoList = new ArrayList<>();

        String sql = "SELECT * from user WHERE name = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, user);

        var dto = new UserDto();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String user_id = resultSet.getString(1);
            String pswd = resultSet.getString(2);
            String name = resultSet.getString(3);
            String address = resultSet.getString(4);
            String email = resultSet.getString(5);
            byte [] image = resultSet.getBytes(6);


            dto = new UserDto(user_id, pswd, name, address, email, image);
            return dto;


        }
        return dto;
    }

    public boolean updateUser(UserDto dto) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE user SET password = ? , name = ? , address = ? , e_mail = ? , image_data = ? WHERE user_id = ?";


        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getPassword());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getAddress());
        pstm.setString(4, dto.getEmail());
        pstm.setBytes(5, dto.getImage());
        pstm.setString(6, dto.getId());


        return pstm.executeUpdate() > 0;

    }

    public byte[] loadImg(String userID) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        List<UserDto> dtoList = new ArrayList<>();

        String sql = "SELECT * from user WHERE  user_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, userID);

        var dto = new UserDto();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String user_id = resultSet.getString(1);
            String pswd = resultSet.getString(2);
            String name = resultSet.getString(3);
            String address = resultSet.getString(4);
            String email = resultSet.getString(5);
            byte [] image = resultSet.getBytes(6);


            dto = new UserDto(user_id, pswd, name, address, email, image);
            return dto.getImage();

        }
        return dto.getImage();

    }

    public boolean updatePassword(String text, String name) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE user SET password = ?WHERE user_id = ?";


        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, text);
        pstm.setString(2, name);


        return pstm.executeUpdate() > 0;


    }
}
