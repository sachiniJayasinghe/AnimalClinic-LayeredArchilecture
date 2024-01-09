package lk.ijse.animal_clinic.bo;

import javafx.event.ActionEvent;
import lk.ijse.animal_clinic.bo.custom.ChangePasswordBO;
import lk.ijse.animal_clinic.dao.DAOFactory;
import lk.ijse.animal_clinic.dao.custom.UserDAO;
import lk.ijse.animal_clinic.dao.custom.impl.UserDAOImpl;
import lk.ijse.animal_clinic.dto.UserDto;

import java.sql.SQLException;

public class ChangePasswordBOImpl implements ChangePasswordBO {
   UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.User);


    @Override
    public boolean updatePassword(String text, String name) throws SQLException, ClassNotFoundException {
        return userDAO.updatePassword(text,name);
    }
}
