package lk.ijse.animal_clinic.dao.custom.impl;

import lk.ijse.animal_clinic.dao.SQLUtil;
import lk.ijse.animal_clinic.dao.custom.SupliyerDAO;
import lk.ijse.animal_clinic.dto.SalaryDto;
import lk.ijse.animal_clinic.dto.StockDto;
import lk.ijse.animal_clinic.dto.SupliyerDto;
import lk.ijse.animal_clinic.dto.customerDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupliyerDAOImpl implements SupliyerDAO {
    @Override
    public ArrayList<SupliyerDto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM supliyer");
        ArrayList<SupliyerDto> allSupliyer= new ArrayList<>();

        while (rst.next()) {
            SupliyerDto entity = new SupliyerDto(
                    rst.getString("supliyer_id"),
                    rst.getString("stock_id"),
                    rst.getString("supliyer_name"),
                    rst.getString("tel"),
                    rst.getString("e_mail"),
                    rst.getString("address"));

            allSupliyer.add(entity);
        }
        return allSupliyer;
    }

    @Override
    public boolean save(SupliyerDto dto) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO supliyer VALUES(?,?,?,?,?,?)";
        return SQLUtil.execute(sql, dto.getSupliyer_id(), dto.getStock_id(), dto.getSupliyer_name(),
                dto.getTel(), dto.getE_mail(), dto.getAddress());
    }

    @Override
    public boolean update(SupliyerDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE supliyer  SET  stock_id = ? , supliyer_name = ? , tel=?,e_mail=? , address = ?  WHERE supliyer_id =?", dto.getStock_id(),dto.getSupliyer_name(),
                dto.getTel(), dto.getE_mail(), dto.getAddress(),dto.getSupliyer_id());
    }
    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT supliyer_id FROM supliyer WHERE supliyer_id=?", id);
        return rst.next();
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM supliyer WHERE supliyer_id = ?";
        return SQLUtil.execute(sql, id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT supliyer_id FROM supliyer ORDER BY supliyer_id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("supliyer_id");
            int newPetId = Integer.parseInt(id.replace("SP0-", "")) + 1;
            return String.format("SP0-%03d", newPetId);
        } else
            return "SP001";
    }

    @Override
    public SupliyerDto search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM supliyer WHERE supliyer_id = ?", id);
        rst.next();
        return new SupliyerDto(id + "", rst.getString("stock_id"), rst.getString("supliyer_name"),rst.getString("tel"), rst.getString("e_mail"), rst.getString("address"));
    }
}
