package lk.ijse.animal_clinic.dao.custom.impl;

import lk.ijse.animal_clinic.dao.SQLUtil;
import lk.ijse.animal_clinic.dao.custom.StockDAO;
import lk.ijse.animal_clinic.dto.SalaryDto;
import lk.ijse.animal_clinic.dto.StockDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockDAOImpl implements StockDAO {
    @Override
    public ArrayList<StockDto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM stock");
        ArrayList<StockDto> allStock= new ArrayList<>();

        while (rst.next()) {
            StockDto entity = new StockDto(
                    rst.getString("stock_id"),
                    rst.getString("category"),
                    rst.getDate("date"));

            allStock.add(entity);
        }
        return allStock;
    }

    @Override
    public boolean save(StockDto dto) throws SQLException, ClassNotFoundException {

        return SQLUtil.
                execute("INSERT INTO stock VALUES(?, ?, ?)",
                        dto.getStock_id(), dto.getCategory(), dto.getDate());
    }


    @Override
    public boolean update(StockDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.
                execute("UPDATE stock SET  category = ? , date= ?  WHERE stock_id =?",
                        dto.getStock_id(), dto.getCategory(), dto.getDate());
    }

    @Override
    public boolean exist(String  stockId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM stock WHERE id=?", stockId);
        return rst.next();
    }

    @Override
    public boolean delete(String stockId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM stock WHERE stock_id = ?";
        return SQLUtil.execute(sql, stockId);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM stock ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newPetId = Integer.parseInt(id.replace("ST0-", "")) + 1;
            return String.format("ST0-%03d", newPetId);
        } else
            return "ST001";
    }

    @Override
    public StockDto search(String stockId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM stock WHERE stock_id = ?";
        ResultSet rst = SQLUtil.execute("SELECT * FROM stock WHERE stock_id = ?", stockId);
        rst.next();
        return new StockDto(stockId + "", rst.getString(" category "), rst.getDate("date"));


    }
    }
