package lk.ijse.animal_clinic.bo;

import lk.ijse.animal_clinic.bo.custom.LoginFormBO;
import lk.ijse.animal_clinic.dao.DAOFactory;
import lk.ijse.animal_clinic.dao.custom.UserDAO;
import lk.ijse.animal_clinic.dao.custom.impl.UserDAOImpl;
import lk.ijse.animal_clinic.dto.UserDto;

import java.sql.SQLException;

public class LoginFormBOImpl implements LoginFormBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.User);
    @Override
    public UserDto search(String user) throws SQLException, ClassNotFoundException {
        return userDAO.search(user);
    }
}
