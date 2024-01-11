package lk.ijse.animal_clinic.bo;

import lk.ijse.animal_clinic.bo.custom.InterventionBO;
import lk.ijse.animal_clinic.dao.DAOFactory;
import lk.ijse.animal_clinic.dao.custom.*;
import lk.ijse.animal_clinic.db.DbConnection;
import lk.ijse.animal_clinic.dto.AppointmentDto;
import lk.ijse.animal_clinic.dto.ThreatmentDetailsDto;
import lk.ijse.animal_clinic.dto.TreatementDto;
import lk.ijse.animal_clinic.dto.customerDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class InterventionBOImpl implements InterventionBO {

    ThreatmentDetailDAO threatmentDetailDAO =  (ThreatmentDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ThreatmentDetails);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Payment);
    CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Customer);
    ThreatmentDAO threatmentDAO = (ThreatmentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Threatement);
    AppointmentDAO appointmentDAO = (AppointmentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Appointement);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Query);
    @Override
    public boolean savePayment(String value, String threatmentId, String loadPayementID, double total) throws SQLException {

        Connection con = null;
        try {
            con = DbConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            var dto = new ThreatmentDetailsDto(
                    value,
                    threatmentId,
                    0.0,
                    null,
                    total
            );
            boolean isThreatmentTotalisSaved = threatmentDetailDAO.save(dto);


            if (isThreatmentTotalisSaved) {

                boolean isPayementSaved = paymentDAO.savePaymentforThreatment(value, loadPayementID, total);

                System.out.println(isPayementSaved);

                if (isPayementSaved) {
                    con.commit();
                    return true;
                }
            }
            return false;
        } catch (SQLException er) {
            con.rollback();
            return false;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("finally");
            con.setAutoCommit(true);
        }

    }

    @Override
    public String getThreatmentId(String value) throws SQLException, ClassNotFoundException {
        return threatmentDAO.getThreatmentId(value);
    }

    @Override
    public customerDto search(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.search(id);

    }

    @Override
    public double getCost(String value) throws SQLException, ClassNotFoundException {
        return threatmentDAO.getCost(value);
    }

    @Override
    public boolean save(ThreatmentDetailsDto dto) throws SQLException, ClassNotFoundException {
        return threatmentDetailDAO.save(new ThreatmentDetailsDto(dto.getAppointmentId(),dto.getTreatementId(),dto.getCost(), dto.getType(), dto.getTotal()));
    }

    @Override
    public List<AppointmentDto> getAll() throws SQLException, ClassNotFoundException {
        return appointmentDAO.getAll();
    }

    @Override
    public List<TreatementDto> getAllTreatement() throws SQLException, ClassNotFoundException {
        return threatmentDAO.getAll();
    }
    @Override
    public int searchPaymentId() throws SQLException {
        return paymentDAO.searchPaymentId();
    }

    public String getCustomerId(String value) throws SQLException, ClassNotFoundException {
        return queryDAO.getCustomerId(value);
    }


}
