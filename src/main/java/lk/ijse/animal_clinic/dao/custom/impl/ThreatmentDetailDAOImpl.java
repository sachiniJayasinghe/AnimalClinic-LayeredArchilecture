package lk.ijse.animal_clinic.dao.custom.impl;

import lk.ijse.animal_clinic.dao.SQLUtil;
import lk.ijse.animal_clinic.dao.custom.ThreatmentDetailDAO;
import lk.ijse.animal_clinic.dto.SupliyerDto;
import lk.ijse.animal_clinic.dto.ThreatmentDetailsDto;
import lk.ijse.animal_clinic.dto.customerDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThreatmentDetailDAOImpl implements ThreatmentDetailDAO {

    @Override

    public boolean save(ThreatmentDetailsDto dto) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO treatementDetails VALUES(?,?,?,?,?)";
        return SQLUtil.execute(sql, dto.getAppointmentId(), dto.getTreatementId(), dto.getCost(),
                dto.getType(), dto.getTotal());
    }

    @Override
    public boolean update(ThreatmentDetailsDto dto) throws SQLException, ClassNotFoundException {
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
    public ThreatmentDetailsDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
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

    @Override
    public List<ThreatmentDetailsDto> extractThreatmentDetailsDtoList(ResultSet resultSet) throws SQLException {
        List<ThreatmentDetailsDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            String appID = resultSet.getString("app_id");
            String thrId = resultSet.getString("treatement_id");
            double cost = resultSet.getDouble("cost");
            String type = resultSet.getString("type");
            double total = resultSet.getDouble("total");

            var dto = new ThreatmentDetailsDto(appID, thrId, cost, type, total);
            dtoList.add(dto);
        }
        return dtoList;

    }
        @Override
        public ArrayList<ThreatmentDetailsDto> getAll() throws SQLException, ClassNotFoundException {
            ResultSet rst = SQLUtil.execute("SELECT * FROM treatementDetails");
            ArrayList<ThreatmentDetailsDto> allCustomer = new ArrayList<>();

            while (rst.next()) {
                ThreatmentDetailsDto entity = new ThreatmentDetailsDto(
                        rst.getString("app_id"),
                        rst.getString("treatement_id"),
                        rst.getDouble("cost"),
                        rst.getString("type"),
                        rst.getDouble("total"));
                allCustomer.add(entity);
            }
            return allCustomer;
        }
}