package lk.ijse.animal_clinic.dao.custom.impl;

import lk.ijse.animal_clinic.dao.SQLUtil;
import lk.ijse.animal_clinic.dao.custom.QueryDAO;
import lk.ijse.animal_clinic.dto.customerDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDAOImpl implements QueryDAO {
    public String getCustomerId(String value) throws SQLException, ClassNotFoundException {
        String sql = "SELECT c.cus_id FROM treatementDetails td " +
                "JOIN appointements a ON td.app_id = a.app_id " +
                "JOIN pet p ON a.pet_id = p.pet_id " +
                "JOIN customer c ON p.cus_id = c.cus_id " +
                "WHERE td.app_id = ?";

        ResultSet resultSet = SQLUtil.execute(sql, value);
        String id = null;
        if (resultSet.next()) {
            id = resultSet.getString("cus_id");
        }
        return id;
    }

    public customerDto loadAll(String appId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT c.* FROM customer c JOIN pet p ON c.cus_id = p.cus_id JOIN appointements a ON p.pet_id = a.pet_id WHERE a.app_id = ?";
        ResultSet resultSet = SQLUtil.execute(sql, appId);

        customerDto dto = null;

        if (resultSet.next()) {
            String cus_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String tel = resultSet.getString(3);
            String e_mail = resultSet.getString(4);
            String address = resultSet.getString(5);

            dto = new customerDto(cus_id, name, tel, e_mail, address);
        }

        return dto;
    }
}
