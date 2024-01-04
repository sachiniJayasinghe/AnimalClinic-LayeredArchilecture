package lk.ijse.animal_clinic.dao.custom;

import lk.ijse.animal_clinic.dao.CrudDAO;
import lk.ijse.animal_clinic.dto.PetDto;

import java.sql.SQLException;

public interface PetDAO extends CrudDAO<PetDto> {
    public int getPetCount() throws SQLException, ClassNotFoundException;
}
