package lk.ijse.animal_clinic.bo;

import lk.ijse.animal_clinic.bo.custom.SupliyerBO;
import lk.ijse.animal_clinic.dao.DAOFactory;
import lk.ijse.animal_clinic.dao.custom.StockDAO;
import lk.ijse.animal_clinic.dao.custom.SupliyerDAO;
import lk.ijse.animal_clinic.dao.custom.impl.StockDAOImpl;
import lk.ijse.animal_clinic.dao.custom.impl.SupliyerDAOImpl;
import lk.ijse.animal_clinic.dto.StockDto;
import lk.ijse.animal_clinic.dto.SupliyerDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupliyerBOImpl implements SupliyerBO {
    SupliyerDAO supliyerDAO =  (SupliyerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Supliyer);

    StockDAO stockDAO =  (StockDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Stock);
    @Override
    public ArrayList<SupliyerDto> getAll() throws SQLException, ClassNotFoundException {
        return supliyerDAO.getAll() ;
    }

    @Override
    public boolean save(SupliyerDto dto) throws SQLException, ClassNotFoundException {
        return supliyerDAO.save(new SupliyerDto(dto.getSupliyer_id(), dto.getStock_id(), dto.getSupliyer_name(),
                dto.getTel(), dto.getE_mail(), dto.getAddress()));
    }

    @Override
    public boolean update(SupliyerDto dto) throws SQLException, ClassNotFoundException {
        return supliyerDAO.update(new SupliyerDto(dto.getSupliyer_id(), dto.getStock_id(), dto.getSupliyer_name(),
                dto.getTel(), dto.getE_mail(), dto.getAddress()));    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return  supliyerDAO.exist(id);
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return  supliyerDAO.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return supliyerDAO.generateNewID();
    }

    @Override
    public SupliyerDto search(String id) throws SQLException, ClassNotFoundException {
        return supliyerDAO.search(id);
    }

    @Override
    public List<StockDto> getAllStock() throws SQLException, ClassNotFoundException {
        return stockDAO.getAll();
    }
}
