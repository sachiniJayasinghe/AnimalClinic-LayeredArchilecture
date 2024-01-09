package lk.ijse.animal_clinic.bo;

import lk.ijse.animal_clinic.bo.custom.EditProfileBO;
import lk.ijse.animal_clinic.dao.DAOFactory;
import lk.ijse.animal_clinic.dao.custom.UserDAO;
import lk.ijse.animal_clinic.dao.custom.impl.UserDAOImpl;
import lk.ijse.animal_clinic.dto.UserDto;

import java.sql.SQLException;


public class EditProfileBOImpl implements EditProfileBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.User);

    @Override
    public byte[] loadImg(String userID) throws SQLException, ClassNotFoundException {
        return userDAO.loadImg(userID);
    }

    @Override
    public boolean updateUser(UserDto dto) throws SQLException, ClassNotFoundException {
        return userDAO.update(new UserDto(dto.getId(), dto.getPassword(), dto.getName(), dto.getAddress(), dto.getEmail(), dto.getImage()));
    }


}
