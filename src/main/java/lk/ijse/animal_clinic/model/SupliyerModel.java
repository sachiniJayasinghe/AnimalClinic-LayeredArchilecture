package lk.ijse.animal_clinic.model;

import lk.ijse.animal_clinic.db.DbConnection;
import lk.ijse.animal_clinic.dto.EmployeeDto;
import lk.ijse.animal_clinic.dto.SupliyerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupliyerModel {
    public boolean saveSupliyer( final SupliyerDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "Insert into supliyer values(?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getSupliyer_id());
        pstm.setString(2, dto.getStock_id());
        pstm.setString(3, dto.getSupliyer_name());
        pstm.setString(4, dto.getTel());
        pstm.setString(5, dto.getE_mail());
        pstm.setString(6, dto.getAddress());


        boolean isSaved = pstm.executeUpdate()>0;

        return  isSaved;


    }

    public boolean updateSupliyer(final  SupliyerDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE supliyer  SET  stock_id = ? , supliyer_name = ? , tel=?,e_mail=? , address = ?  WHERE supliyer_id =?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getStock_id());
        pstm.setString(2, dto.getSupliyer_name());
        pstm.setString(3, dto.getTel());
        pstm.setString(4, dto.getE_mail());
        pstm.setString(5, dto.getAddress());
        pstm.setString(6, dto.getSupliyer_id());




        return pstm.executeUpdate() > 0;



    }

    public boolean deleteSupliyer(String supliyerId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM supliyer WHERE supliyer_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, supliyerId);

        return pstm.executeUpdate() > 0;
    }

    public SupliyerDto searchSupliyer(String supliyerId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM supliyer WHERE supliyer_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, supliyerId);

        ResultSet resultSet = pstm.executeQuery();

        SupliyerDto dto = null;

        if(resultSet.next()) {
            String supliyer_id = resultSet.getString(1);
            String stock_id = resultSet.getString(2);
            String supliyer_name = resultSet.getString(3);
            String tel = resultSet.getString(4);
            String e_mail = resultSet.getString(5);
            String address = resultSet.getString(6);



            dto = new SupliyerDto(supliyer_id, stock_id, supliyer_name,tel,e_mail,address);
        }

        return dto;
    }

    public List<SupliyerDto> getAllSupliyer() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM supliyer";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<SupliyerDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String supliyer_id = resultSet.getString(1);
            String stock_id = resultSet.getString(2);
            String supliyer_name = resultSet.getString(3);
            String tel = resultSet.getString(4);
            String e_mail = resultSet.getString(5);
            String address = resultSet.getString(6);



          var  dto = new SupliyerDto(supliyer_id, stock_id, supliyer_name,tel,e_mail,address);

            dtoList.add(dto);
        }
        return dtoList;
    }
}

