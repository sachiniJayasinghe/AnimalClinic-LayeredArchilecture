package lk.ijse.animal_clinic.model;

import lk.ijse.animal_clinic.db.DbConnection;
import lk.ijse.animal_clinic.dto.AppointmentDto;
import lk.ijse.animal_clinic.dto.SalaryDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalaryModel {

    public boolean saveSalary(final  SalaryDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO salary VALUES(?, ?, ?, ?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getSalary_id());
        pstm.setString(2, dto.getEmp_id());
        pstm.setDate(3, dto.getDate());
        pstm.setString(4, dto.getSalary_month());
        pstm.setDouble(5, dto.getAmount());




        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;

    }

    public boolean updateSalary(final SalaryDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE salary SET  emp_id = ?  , date=? , salary_month= ? , amount=? WHERE salary_id =?";
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setString(1, dto.getEmp_id());
        pstm.setDate(2, dto.getDate());
        pstm.setString(3, dto.getSalary_month());
        pstm.setDouble(4, dto.getAmount());
        pstm.setString(5, dto.getSalary_id());



        return pstm.executeUpdate() > 0;


    }

    public boolean deleteSalary(String salaryId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM salary WHERE salary_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, salaryId);

        return pstm.executeUpdate() > 0;
    }

    public SalaryDto searchSalary(String salaryId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM salary WHERE salary_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, salaryId);

        ResultSet resultSet = pstm.executeQuery();

        SalaryDto dto = null;

        if(resultSet.next()) {
            String salary_id = resultSet.getString(1);
            String emp_id = resultSet.getString(2);
            Date date = Date.valueOf(resultSet.getString(3));
            String salary_month = resultSet.getString(4);
            double amount = resultSet.getDouble(5);



            dto = new SalaryDto(salary_id, emp_id,date,salary_month,amount);
        }

        return dto;

    }

    public List<SalaryDto> getAllSalary() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM salary";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<SalaryDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String salary_id = resultSet.getString(1);
            String emp_id = resultSet.getString(2);
            Date date = Date.valueOf(resultSet.getString(3));
            String salary_month = resultSet.getString(4);
            double amount = resultSet.getDouble(5);



            var dto = new SalaryDto(salary_id, emp_id,date,salary_month,amount);

            dtoList.add(dto);
        }
        return dtoList;
    }
    }

