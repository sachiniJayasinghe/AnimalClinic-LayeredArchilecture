package lk.ijse.animal_clinic.entity;

import java.sql.Date;

public class Treatement {
    private String treatement_id;
    private String vaccination_id;
    private String type;
    private double cost;
    private Date date;
    public Treatement(){}
    public Treatement(String treatement_id, String vaccination_id, String type, double cost, Date date) {
        this.treatement_id = treatement_id;
        this.vaccination_id = vaccination_id;
        this.type = type;
        this.cost = cost;
        this.date = date;
    }

    public String getTreatement_id() {
        return treatement_id;
    }

    public void setTreatement_id(String treatement_id) {
        this.treatement_id = treatement_id;
    }

    public String getVaccination_id() {
        return vaccination_id;
    }

    public void setVaccination_id(String vaccination_id) {
        this.vaccination_id = vaccination_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Treatement{" +
                "treatement_id='" + treatement_id + '\'' +
                ", vaccination_id='" + vaccination_id + '\'' +
                ", type='" + type + '\'' +
                ", cost=" + cost +
                ", date=" + date +
                '}';
    }
}
