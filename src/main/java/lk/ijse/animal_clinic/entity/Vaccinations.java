package lk.ijse.animal_clinic.entity;

import java.sql.Date;

public class Vaccinations {
    private String vaccination_id;
    private String VaccinationName;
    private Date next_dueDate;
    public Vaccinations(){}
    public Vaccinations(String vaccination_id, String vaccinationName, Date next_dueDate) {
        this.vaccination_id = vaccination_id;
        VaccinationName = vaccinationName;
        this.next_dueDate = next_dueDate;
    }


    public String getVaccination_id() {
        return vaccination_id;
    }

    public void setVaccination_id(String vaccination_id) {
        this.vaccination_id = vaccination_id;
    }

    public String getVaccinationName() {
        return VaccinationName;
    }

    public void setVaccinationName(String vaccinationName) {
        VaccinationName = vaccinationName;
    }

    public Date getNext_dueDate() {
        return next_dueDate;
    }

    public void setNext_dueDate(Date next_dueDate) {
        this.next_dueDate = next_dueDate;
    }



}
