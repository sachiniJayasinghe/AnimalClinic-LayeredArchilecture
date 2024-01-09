package lk.ijse.animal_clinic.bo;

import lk.ijse.animal_clinic.bo.custom.ConfirmPasswordBO;
import lk.ijse.animal_clinic.dao.DAOFactory;
import lk.ijse.animal_clinic.dao.custom.UserDAO;
import lk.ijse.animal_clinic.dao.custom.impl.UserDAOImpl;
import lk.ijse.animal_clinic.dto.UserDto;

import java.sql.SQLException;

public class ConfirmPasswordBOImpl implements ConfirmPasswordBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.User);
    @Override
    public boolean save(UserDto dto) throws SQLException, ClassNotFoundException {
        return userDAO.save(new UserDto(dto.getId(), dto.getPassword(), dto.getName(),  dto.getAddress(),dto.getEmail(), dto.getImage()));
    }
}
