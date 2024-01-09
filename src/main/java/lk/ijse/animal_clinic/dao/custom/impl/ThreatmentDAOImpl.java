package lk.ijse.animal_clinic.dao.custom.impl;

import lk.ijse.animal_clinic.dao.SQLUtil;
import lk.ijse.animal_clinic.dao.custom.ThreatmentDAO;
import lk.ijse.animal_clinic.dto.TreatementDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class ThreatmentDAOImpl implements ThreatmentDAO {
@Override
    public boolean save(final TreatementDto dto) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO treatement VALUES(?, ?, ?, ?,?)";
        return SQLUtil.execute(sql, dto.getTreatement_id(), dto.getVaccination_id(), dto.getType(),
                dto.getCost(), dto.getDate());
    }
@Override
    public boolean update(final TreatementDto dto) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE treatement SET  vaccination_id = ? , type= ? ,cost=?,date=? WHERE treatement_id =?";
        return SQLUtil.execute(sql, dto.getVaccination_id(), dto.getType(), dto.getCost(),
                dto.getDate(), dto.getTreatement_id());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM treatement  WHERE id=?", id);
        return rst.next();
    }
    @Override
    public boolean delete(String treatementId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM treatement WHERE treatement_id = ?";
        return SQLUtil.execute(sql, treatementId);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT treatement_id FROM treatement ORDER BY treatement_id DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("treatement_id");
            int newCustomerId = Integer.parseInt(id.replace("T00", "")) + 1;
            return String.format("T%03d", newCustomerId);
        } else {
            return "T001";
        }

    }
    @Override
    public TreatementDto search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM treatement WHERE treatement_id = ?";
        ResultSet resultSet = SQLUtil.execute(sql, id);
        return extractTreatementDto(resultSet);
    }

    @Override
    public ArrayList<TreatementDto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM treatement");
        ArrayList<TreatementDto> allThreatment = new ArrayList<>();

        while (rst.next()) {
            TreatementDto entity = new TreatementDto(
                    rst.getString("treatement_id"),
                    rst.getString("vaccination_id"),
                    rst.getString("type"),
                    rst.getDouble("cost"),
                    rst.getDate("date"));
            allThreatment.add(entity);
        }
        return allThreatment;
    }
    @Override
    public double getCost(String value) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM treatement WHERE type = ?";
        ResultSet resultSet = SQLUtil.execute(sql, value);
        List<TreatementDto> dtoList = extractTreatementDtoList(resultSet);

        if (!dtoList.isEmpty()) {
            return dtoList.get(0).getCost();
        } else {
            return 0.0;
        }
    }
@Override
    public String getThreatmentId(String value) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM treatement WHERE type = ?";
        ResultSet resultSet = SQLUtil.execute(sql, value);
        List<TreatementDto> dtoList = extractTreatementDtoList(resultSet);

        if (!dtoList.isEmpty()) {
            return dtoList.get(0).getTreatement_id();
        } else {
            return null;
        }
    }

  @Override
    public TreatementDto extractTreatementDto(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            String treatement_id = resultSet.getString("treatement_id");
            String vaccination_id = resultSet.getString("vaccination_id");
            String type = resultSet.getString("type");
            double cost = resultSet.getDouble("cost");
            Date date = resultSet.getDate("date");

            return new TreatementDto(treatement_id, vaccination_id, type, cost, date);
        }
        return null;
    }
@Override
    public List<TreatementDto> extractTreatementDtoList(ResultSet resultSet) throws SQLException {
        List<TreatementDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            String treatement_id = resultSet.getString("treatement_id");
            String vaccination_id = resultSet.getString("vaccination_id");
            String type = resultSet.getString("type");
            double cost = resultSet.getDouble("cost");
            Date date = resultSet.getDate("date");

            var dto = new TreatementDto(treatement_id, vaccination_id, type, cost, date);
            dtoList.add(dto);
        }
        return dtoList;
    }
}
