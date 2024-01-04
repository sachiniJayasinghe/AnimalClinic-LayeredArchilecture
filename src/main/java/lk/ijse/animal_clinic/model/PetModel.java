package lk.ijse.animal_clinic.model;

import com.mysql.cj.xdevapi.Result;
import lk.ijse.animal_clinic.db.DbConnection;
import lk.ijse.animal_clinic.dto.PetDto;
import lk.ijse.animal_clinic.dto.customerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetModel {
    public static PetDto searchPet(String pet_id) throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql = "select * from pet where pet_id = ?";
        PreparedStatement pstm= connection.prepareStatement(sql);
        pstm.setString(1,pet_id);

        ResultSet resultSet = pstm.executeQuery();

        PetDto dto = null;
        if (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String pet_type = resultSet.getString(3);
            String age = resultSet.getString(4);
            String cus_id = resultSet.getString(5);


            dto = new PetDto(id, name, pet_type, age, cus_id);

        }
        return  dto;
    }

    public boolean savePet(final PetDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "Insert into pet values(?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getPet_id());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getPet_type());
        pstm.setString(4, dto.getAge());
        pstm.setString(5, dto.getCus_id());

        boolean isSaved = pstm.executeUpdate()>0;

        return  isSaved;




    }

    public boolean updatePet(final PetDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "Update pet SET name =?, pet_type=?, age=?,cus_id =? WHERE pet_id =?";

        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getPet_type());
        pstm.setString(3, dto.getAge());
        pstm.setString(4, dto.getCus_id());
        pstm.setString(5, dto.getPet_id());

        boolean isUpdated = pstm.executeUpdate()>0;
        return isUpdated;



    }

    public boolean deletePet(String pet_id) throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();

        String sql = "delete  from pet where pet_id =?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,pet_id);

        return  pstm.executeUpdate()>0;
    }
    public static List<PetDto> getAllPet() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM pet";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<PetDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String pet_type = resultSet.getString(3);
            String age = resultSet.getString(4);
            String cus_id = resultSet.getString(5);

            var dto = new PetDto(id, name, pet_type,age,cus_id);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public int getPetCount() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT COUNT(*) FROM pet";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = pstm.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } finally {
        }
        return 0;

    }

    public String generateNextPetId() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT pet_id FROM pet ORDER BY pet_id DESC LIMIT 1";
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        String currentOrderId = null;

        if (resultSet.next()) {
            currentOrderId = resultSet.getString(1);
            return splitOrderId(currentOrderId);
        }
        return splitOrderId(null);
    }

    private String splitOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("P");
            int id = Integer.parseInt(split[1]);
            id++;

            String formattedId = String.format("%03d", id);

            return "P" + formattedId;
        }
        return "P001";
    }
}
