package lk.ijse.animal_clinic.bo;

import lk.ijse.animal_clinic.bo.custom.StockBO;
import lk.ijse.animal_clinic.dao.custom.StockDAO;
import lk.ijse.animal_clinic.dao.custom.impl.StockDAOImpl;
import lk.ijse.animal_clinic.dto.StockDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class StockBOImpl implements StockBO {
    StockDAO stockDAO = new StockDAOImpl();
    @Override
    public ArrayList<StockDto> getAll() throws SQLException, ClassNotFoundException {
        return stockDAO.getAll();
    }

    @Override
    public boolean save(StockDto dto) throws SQLException, ClassNotFoundException {
        return stockDAO.save( new StockDto( dto.getStock_id(), dto.getCategory(), dto.getDate()));
    }

    @Override
    public boolean update(StockDto dto) throws SQLException, ClassNotFoundException {
        return stockDAO.update( new StockDto( dto.getStock_id(), dto.getCategory(), dto.getDate()));
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return stockDAO.exist(id);
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return stockDAO.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return stockDAO.generateNewID();
    }

    @Override
    public StockDto search(String id) throws SQLException, ClassNotFoundException {
        return stockDAO.search(id);
    }
}
