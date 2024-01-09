package lk.ijse.animal_clinic.dao.custom.impl;

import lk.ijse.animal_clinic.dao.SQLUtil;
import lk.ijse.animal_clinic.dao.custom.PaymentDAO;
import lk.ijse.animal_clinic.db.DbConnection;
import lk.ijse.animal_clinic.dto.PaymentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public ArrayList<PaymentDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(PaymentDto dto) throws SQLException, ClassNotFoundException {

    return false;
    }

    @Override
    public boolean update(PaymentDto dto) throws SQLException, ClassNotFoundException {
        return false;
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
    public PaymentDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
@Override
    public int searchID(String id) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT MAX(p_id) FROM payment");
        int idl = 0;

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            idl = resultSet.getInt(1);
        }
        return idl;
    }

    @Override
    public int searchPaymentId() throws SQLException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT MAX(p_id) FROM payment");
        int id = 0;

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            id = resultSet.getInt(1);
        }
        return id;
    }

    @Override
    public boolean savePaymentforThreatment(String value, String loadPayementID, double total) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "Insert into payment values(?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,loadPayementID);
        pstm.setString(2,value);
        pstm.setDouble(3, total);

        boolean isSaved = pstm.executeUpdate()>0;
        return isSaved;
    }
}
