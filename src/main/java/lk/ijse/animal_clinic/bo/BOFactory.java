package lk.ijse.animal_clinic.bo;

public class BOFactory {
    private static  BOFactory boFactory;
    private BOFactory(){

    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory = new BOFactory():boFactory;
    }

    public enum BOTypes{
        AppointmentBO,ChangePasswordBO,ConfirmPasswordBO,CustomerBO,DoctorBO,EditProfileBO,EmployeeBO,InterventionBO,LoginFormBO,PetBO,SalaryBO,StockBO,SupliyerBO,ThreatmentBO,VaccinationsBO
    }
    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case AppointmentBO:
                return new AppointementBOImpl();
            case ChangePasswordBO:
                return new ChangePasswordBOImpl();
            case ConfirmPasswordBO:
                return new ConfirmPasswordBOImpl();
            case CustomerBO:
                return new CustomerBOImpl();
            case DoctorBO:
                return new DoctorBOImpl();
            case EditProfileBO:
                return new EditProfileBOImpl();
            case EmployeeBO:
                return new EmployeeBOImpl();
            case InterventionBO:
                return new InterventionBOImpl();
            case LoginFormBO:
                return new LoginFormBOImpl();
            case PetBO:
                return new PetBOImpl();
            case SalaryBO:
                return new SalaryBOImpl();
            case StockBO:
                return new StockBOImpl();
            case SupliyerBO:
                return new SupliyerBOImpl();
            case ThreatmentBO:
                return new ThreatmentBOImpl();
            case VaccinationsBO:
                return new VaccinationsBOImpl();
            default:
                return null;
        }

    }
}
