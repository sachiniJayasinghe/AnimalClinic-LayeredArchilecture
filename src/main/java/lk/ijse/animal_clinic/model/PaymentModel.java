package lk.ijse.animal_clinic.model;

import lk.ijse.animal_clinic.db.DbConnection;
import lk.ijse.animal_clinic.dto.ThreatmentDetailsDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentModel {
    public static int search() throws SQLException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT MAX(p_id) FROM payment");
        int id = 0;

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            id = resultSet.getInt(1);
        }
        return id;


    }


    public boolean savePayment(String value, String threatmentId ,String loadPayementID, double total) throws SQLException {


        Connection con = null;
        try {
            con = DbConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            var threatmentDetailsModel = new ThreatmentDetailModel();
            var dto = new ThreatmentDetailsDto(
                    value,
                    threatmentId,
                    0.0,
                    null,
                    total
            );
            boolean isThreatmentTotalisSaved = threatmentDetailsModel.saveThreatmentDetails(dto);


            System.out.println(isThreatmentTotalisSaved);

            if (isThreatmentTotalisSaved) {

                boolean isPayementSaved = savePaymentforThreatment(value, loadPayementID, total);

                System.out.println(isPayementSaved);

                if (isPayementSaved) {
                    con.commit();
                    return true;
                }
            }
            return false;
        } catch (SQLException er) {
            con.rollback();
            return false;
        } finally {
            System.out.println("finally");
            con.setAutoCommit(true);
        }
    }

    private boolean savePaymentforThreatment(String value, String loadPayementID, double total) throws SQLException {
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
