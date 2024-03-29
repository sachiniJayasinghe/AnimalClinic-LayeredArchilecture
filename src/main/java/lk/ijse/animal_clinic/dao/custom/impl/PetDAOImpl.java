package lk.ijse.animal_clinic.dao.custom.impl;

import lk.ijse.animal_clinic.dao.SQLUtil;
import lk.ijse.animal_clinic.dao.custom.PetDAO;
import lk.ijse.animal_clinic.dto.PetDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PetDAOImpl implements PetDAO {
    @Override
    public ArrayList<PetDto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM pet");
        ArrayList<PetDto> allPet = new ArrayList<>();

        while (rst.next()) {
            PetDto entity = new PetDto(
                    rst.getString("pet_id"),
                    rst.getString("name"),
                    rst.getString("age"),
                    rst.getString("pet_type"),
                    rst.getString("cus_id"));
            allPet.add(entity);
        }
        return allPet;
    }

    @Override
    public boolean save(PetDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.
                execute("INSERT INTO pet VALUES(?, ?, ?, ?,?)",
                        dto.getPet_id(), dto.getName(), dto.getAge(),dto.getPet_type(), dto.getCus_id());
    }

    @Override
    public boolean update(PetDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("Update pet SET name =?, age=?,pet_type=?,cus_id =? WHERE pet_id =?",
                dto.getName(),  dto.getAge(),dto.getPet_type(), dto.getCus_id(),dto.getPet_id());


    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT pet_id FROM pet WHERE pet_id=?", id);
        return rst.next();
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM pet WHERE pet_id=?", id);

    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT pet_id FROM pet ORDER BY pet_id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("pet_id");
            int newPetId = Integer.parseInt(id.replace("P00", "")) + 1;
            return String.format("P%03d", newPetId);
        } else 
            return "P001";
        }


    @Override
    public PetDto search(String id) throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT * FROM pet WHERE pet_id = ?", id);
        rst.next();
        return new PetDto(id + "", rst.getString("name"),  rst.getString("age"),rst.getString("pet_type"), rst.getString("cus_id"));
    }
    @Override
    public int getPetCount() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM pet";
        try {
            ResultSet resultSet = SQLUtil.execute(sql);
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }
        return 0;


    }


}