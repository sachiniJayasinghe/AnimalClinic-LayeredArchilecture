package lk.ijse.animal_clinic.dao.custom.impl;

import lk.ijse.animal_clinic.dao.SQLUtil;
import lk.ijse.animal_clinic.dao.custom.UserDAO;
import lk.ijse.animal_clinic.dto.UserDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    @Override
    public ArrayList<UserDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override

    public boolean save(UserDto dto) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO user VALUES(?,?,?,?,?,?)";
        return SQLUtil.execute(sql, dto.getId(), dto.getPassword(), dto.getName(),
                dto.getAddress(), dto.getEmail(), dto.getImage());
    }
    @Override

    public UserDto search(String user) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM user WHERE name = ?";
        ResultSet resultSet = SQLUtil.execute(sql, user);
        return extractUserDto(resultSet);
    }
    @Override

    public boolean update(UserDto dto) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE user SET password = ?, name = ?, address = ?, e_mail = ?, image_data = ? WHERE user_id = ?";
        return SQLUtil.execute(sql, dto.getPassword(), dto.getName(), dto.getAddress(),
                dto.getEmail(), dto.getImage(), dto.getId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public byte[] loadImg(String userID) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM user WHERE user_id = ?";
        ResultSet resultSet = SQLUtil.execute(sql, userID);
        UserDto dto = extractUserDto(resultSet);
        return dto != null ? dto.getImage() : null;
    }

    public boolean updatePassword(String text, String name) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE user SET password = ? WHERE user_id = ?";
        return SQLUtil.execute(sql, text, name);
    }

    public UserDto extractUserDto(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            String user_id = resultSet.getString("user_id");
            String pswd = resultSet.getString("password");
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            String email = resultSet.getString("e_mail");
            byte[] image = resultSet.getBytes("image_data");

            return new UserDto(user_id, pswd, name, address, email, image);
        }
        return null;
    }
}
