package lk.ijse.animal_clinic.dao.custom.impl;

import lk.ijse.animal_clinic.dao.SQLUtil;
import lk.ijse.animal_clinic.dao.custom.CustomerDAO;
import lk.ijse.animal_clinic.dto.customerDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean save(final customerDto dto) throws SQLException, ClassNotFoundException {

        return SQLUtil.
                execute("INSERT INTO customer VALUES(?, ?, ?, ?,?)",
                        dto.getId(), dto.getName(), dto.getTel(), dto.getEmail(), dto.getAddress());
    }

    @Override

    public boolean update(final customerDto dto) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE customer  SET  name = ? , tel = ? , e_mail=? , address = ?  WHERE cus_id =?",
                dto.getId(), dto.getName(), dto.getTel(), dto.getEmail(), dto.getAddress());


    }

    @Override
    public customerDto search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer WHERE cus_id = ?", id);
        rst.next();
        return new customerDto(id + "", rst.getString("name"), rst.getString("tel"), rst.getString("e_mail"), rst.getString("address"));
    }


    @Override
    public ArrayList<customerDto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer");
        ArrayList<customerDto> allCustomer = new ArrayList<>();

        while (rst.next()) {
            customerDto entity = new customerDto(
                    rst.getString("cus_id"),
                    rst.getString("name"),
                    rst.getString("tel"),
                    rst.getString("e_mail"),
                    rst.getString("address"));
            allCustomer.add(entity);
        }
        return allCustomer;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM customer WHERE cus_id=?", id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT cus_id FROM customer ORDER BY cus_id DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("cus_id");
            int newCustomerId = Integer.parseInt(id.replace("C00", "")) + 1;
            return String.format("C%03d", newCustomerId);
        } else {
            return "C001";
        }
    }






    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT cus_id FROM customer WHERE cus_id=?", id);
        return rst.next();

    }
    @Override
    public int getCustomerCount() throws ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM customer";
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

}