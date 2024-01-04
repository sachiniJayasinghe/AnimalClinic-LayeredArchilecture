package lk.ijse.animal_clinic.dao.custom.impl;

import lk.ijse.animal_clinic.dao.SQLUtil;
import lk.ijse.animal_clinic.dao.custom.VaccinationsDAO;
import lk.ijse.animal_clinic.dto.vaccinationsDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class vaccinationsDAOImpl implements VaccinationsDAO {
    @Override
    public ArrayList<vaccinationsDto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM vaccinations");
        ArrayList<vaccinationsDto> allPet = new ArrayList<>();

        while (rst.next()) {
            vaccinationsDto entity = new vaccinationsDto(
                    rst.getString("vaccination_id"),
                    rst.getString("name"),
                    rst.getDate(" next_dueDate"));

            allPet.add(entity);
        }
        return allPet;
    }

    @Override
    public boolean save(vaccinationsDto dto) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO vaccinations VALUES(?, ?, ?)";
        return SQLUtil.execute(sql, dto.getVaccination_id(), dto.getVaccinationName(), dto.getNext_dueDate());
    }

    @Override
    public boolean update(vaccinationsDto dto) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE vaccinations SET  vaccine_name = ? , next_dueDate= ?  WHERE vaccination_id =?";
        return SQLUtil.execute(sql, dto.getVaccinationName(), dto.getNext_dueDate(), dto.getVaccination_id());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM vaccinations WHERE id=?", id);
        return rst.next();
    }

    @Override
    public boolean delete(String vaccinationId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM vaccinations WHERE vaccination_id = ?";
        return SQLUtil.execute(sql, vaccinationId);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM vaccinations ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newEmployeeId = Integer.parseInt(id.replace("V0-", "")) + 1;
            return String.format("V0-%03d", newEmployeeId);
        } else {
            return "V001";
        }
    }

    @Override
    public vaccinationsDto search(String id) throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT * FROM vaccinations WHERE pet_id = ?", id);
        rst.next();
        return new vaccinationsDto(id + "", rst.getString("name"), rst.getDate("getNext_dueDat"));
    }
}
