package lk.ijse.animal_clinic.model;

import lk.ijse.animal_clinic.db.DbConnection;
import lk.ijse.animal_clinic.dto.customerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel {
    public boolean saveCustomer(final customerDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO customer VALUES(?, ?, ?, ?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getTel());
        pstm.setString(4, dto.getEmail());
        pstm.setString(5, dto.getAddress());


        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

    public boolean updateCustomer(final customerDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE customer  SET  name = ? , tel = ? , e_mail=? , address = ?  WHERE cus_id =?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getTel());
        pstm.setString(3, dto.getEmail());
        pstm.setString(4, dto.getAddress());
        pstm.setString(5, dto.getId());


        return pstm.executeUpdate() > 0;




    }
    public customerDto searchCustomer(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM customer WHERE cus_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();

        customerDto dto = null;

        if(resultSet.next()) {
            String cus_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String tel = resultSet.getString(3);
            String e_mail = resultSet.getString(4);
            String address = resultSet.getString(5);



            dto = new customerDto(cus_id, name, tel,e_mail,address);
        }

        return dto;
    }


    public static List<customerDto> getAllCustomers() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM customer";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<customerDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String cus_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String tel = resultSet.getString(3);
            String e_mail= resultSet.getString(4);
            String address = resultSet.getString(5);

            var dto = new customerDto(cus_id, name, tel, e_mail ,address);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public boolean deleteCustomer(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM customer WHERE cus_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }

    public int getCustomerCount() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT COUNT(*) FROM customer";
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





    public String generateNextCustomerID() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT cus_id FROM customer ORDER BY cus_id DESC LIMIT 1";
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
            String[] split = currentOrderId.split("C");
            int id = Integer.parseInt(split[1]);
            id++;

            String formattedId = String.format("%03d", id);

            return "C" + formattedId;
        }
        return "C001";
    }

    public customerDto loadAll(String appId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT c.* FROM customer c JOIN pet p ON c.cus_id = p.cus_id JOIN appointements a ON p.pet_id = a.pet_id WHERE a.app_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, appId);

        ResultSet resultSet = pstm.executeQuery();

        customerDto dto = null;

        if(resultSet.next()) {
            String cus_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String tel = resultSet.getString(3);
            String e_mail = resultSet.getString(4);
            String address = resultSet.getString(5);

            dto = new customerDto(cus_id, name, tel,e_mail,address);
        }

        return dto;
    }
}
