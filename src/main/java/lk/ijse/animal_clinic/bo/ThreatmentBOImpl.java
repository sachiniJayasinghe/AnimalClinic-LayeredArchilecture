package lk.ijse.animal_clinic.bo;

import lk.ijse.animal_clinic.bo.custom.ThreatmentBO;
import lk.ijse.animal_clinic.dao.DAOFactory;
import lk.ijse.animal_clinic.dao.custom.ThreatmentDAO;
import lk.ijse.animal_clinic.dao.custom.impl.ThreatmentDAOImpl;
import lk.ijse.animal_clinic.dto.TreatementDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThreatmentBOImpl implements ThreatmentBO {

    ThreatmentDAO threatmentDAO = (ThreatmentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Threatement);
    @Override
    public boolean save(TreatementDto dto) throws SQLException, ClassNotFoundException {
        return threatmentDAO.save(new TreatementDto(dto.getTreatement_id(), dto.getVaccination_id(), dto.getType(),
                dto.getCost(), dto.getDate()));
    }

    @Override
    public boolean update(TreatementDto dto) throws SQLException, ClassNotFoundException {
        return threatmentDAO.update(new TreatementDto(dto.getTreatement_id(), dto.getVaccination_id(), dto.getType(),
                dto.getCost(), dto.getDate()));
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return threatmentDAO.exist(id);
    }

    @Override
    public boolean delete(String treatementId) throws SQLException, ClassNotFoundException {
        return threatmentDAO.exist(treatementId);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return threatmentDAO.generateNewID();
    }

    @Override
    public TreatementDto search(String id) throws SQLException, ClassNotFoundException {
            return threatmentDAO.search(id);
    }

    @Override
    public ArrayList<TreatementDto> getAll() throws SQLException, ClassNotFoundException {
        return threatmentDAO.getAll();
    }

    @Override
    public double getCost(String value) throws SQLException, ClassNotFoundException {
        return threatmentDAO.getCost(value);
    }

    @Override
    public String getThreatmentId(String value) throws SQLException, ClassNotFoundException {
        return threatmentDAO.getThreatmentId(value);
    }

    @Override
    public TreatementDto extractTreatementDto(ResultSet resultSet) throws SQLException {
        return threatmentDAO.extractTreatementDto(resultSet);
    }

    @Override
    public List<TreatementDto> extractTreatementDtoList(ResultSet resultSet) throws SQLException {
        return threatmentDAO.extractTreatementDtoList(resultSet);
    }
}
