package lk.ijse.animal_clinic.dao;

import lk.ijse.animal_clinic.dao.custom.impl.*;

public class DAOFactory {

    private static  DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static  DAOFactory getDaoFactory(){
        return (daoFactory==null)? daoFactory = new DAOFactory():daoFactory;
    }
    public enum DAOTypes{
        Appointement,Customer,Doctor,Employee,Payment,Pet,Salary,Stock,Supliyer,Threatement,ThreatmentDetails,User,Vaccinations,Query
    }
    public SuperDAO getDAO(DAOTypes daoTypes) {
        switch (daoTypes){
            case Appointement:
                return new AppointmentDAOImpl();
            case Customer:
                return new CustomerDAOImpl();
            case Doctor:
                return new DoctorDAOImpl();
            case Employee:
                return new EmployeeDAOImpl();
            case Payment:
                return new PaymentDAOImpl();
            case Pet:
                return new PetDAOImpl();
            case Salary:
                return new SalaryDAOImpl();
            case Stock:
                return  new StockDAOImpl();
            case Supliyer:
                return new SupliyerDAOImpl();
            case Threatement:
                return new ThreatmentDAOImpl();
            case ThreatmentDetails:
                return new ThreatmentDetailDAOImpl();
            case User:
                return  new UserDAOImpl();
            case Vaccinations:
                return  new VaccinationsDAOImpl();
            case Query:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }
}
