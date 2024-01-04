package lk.ijse.animal_clinic.model;

import lk.ijse.animal_clinic.db.DbConnection;
import lk.ijse.animal_clinic.dto.DoctorDto;
import lk.ijse.animal_clinic.dto.ThreatmentDetailsDto;
import lk.ijse.animal_clinic.dto.tm.ThreatmentDetailTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThreatmentDetailModel {

    public static List<ThreatmentDetailsDto> getAllThreatmentDetails() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM treatementDetails";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<ThreatmentDetailsDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String appID = resultSet.getString(1);
            String thrId = resultSet.getString(2);
            double cost = resultSet.getDouble(3);
            String type = resultSet.getString(4);


            var dto = new ThreatmentDetailsDto(appID, thrId, cost, type, 0.0);
            dtoList.add(dto);
        }
        return dtoList;

    }

    public boolean saveThreatmentDetails(ThreatmentDetailsDto dto) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "Insert into treatementDetails values(?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,dto.getAppointmentId());
        pstm.setString(2, dto.getTreatementId());
        pstm.setDouble(3, dto.getCost());
        pstm.setString(4, dto.getType());
        pstm.setDouble(5, dto.getTotal());

        boolean isSaved = pstm.executeUpdate()>0;
        return isSaved;

    }

    public String getCustomerId(String value) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT c.cus_id FROM treatementDetails td JOIN appointements a ON td.app_id = a.app_id JOIN pet p ON a.pet_id = p.pet_id JOIN customer c ON p.cus_id = c.cus_id WHERE td.app_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, value);

        ResultSet resultSet = pstm.executeQuery();

        String id = null;
        if (resultSet.next()) {
            id = resultSet.getString("cus_id");
        }

        return id;
    }

}
