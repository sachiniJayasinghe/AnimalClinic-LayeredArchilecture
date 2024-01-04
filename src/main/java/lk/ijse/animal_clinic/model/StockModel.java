package lk.ijse.animal_clinic.model;

import lk.ijse.animal_clinic.db.DbConnection;
import lk.ijse.animal_clinic.dto.StockDto;
import lk.ijse.animal_clinic.dto.vaccinationsDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockModel {
    public boolean saveStock(final StockDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO stock VALUES(?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getStock_id());
        pstm.setString(2, dto.getCategory());
        pstm.setDate(3, dto.getDate());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;

    }

    public boolean updateStock(final StockDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE stock SET  category = ? , date= ?  WHERE stock_id =?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getCategory());
        pstm.setDate(2, dto.getDate());
        pstm.setString(3, dto.getStock_id());




        return pstm.executeUpdate() > 0;


    }

    public boolean StockModel(String stockId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM stock WHERE stock_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, stockId);

        return pstm.executeUpdate() > 0;
    }

    public StockDto searchStock(String stockId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM stock WHERE stock_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, stockId);

        ResultSet resultSet = pstm.executeQuery();

        StockDto dto = null;

        if(resultSet.next()) {
            String stock_id = resultSet.getString(1);
            String category = resultSet.getString(2);
            Date date = Date.valueOf(resultSet.getString(3));



            dto = new StockDto(stock_id,category , date);
        }

        return dto;

    }

    public static List<StockDto> getAllStock() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM stock";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<StockDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String stock_id = resultSet.getString(1);
            String category = resultSet.getString(2);
            Date date = Date.valueOf(resultSet.getString(3));



           var dto = new StockDto(stock_id,category , date);


            dtoList.add(dto);
        }
        return dtoList;
    }
    }

