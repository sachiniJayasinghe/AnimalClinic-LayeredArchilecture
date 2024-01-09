package lk.ijse.animal_clinic.entity;

import java.sql.Date;
import java.sql.Time;

public class Appointment {

    private String app_id;
    private String doctor_id;
    private String pet_id;
    private Date date;
    private Time time ;
    private int Number;

    public Appointment() {
    }

    public Appointment(String app_id, String doctor_id, String pet_id, Date date, Time time, int number) {
        this.app_id = app_id;
        this.doctor_id = doctor_id;
        this.pet_id = pet_id;
        this.date = date;
        this.time = time;
        Number = number;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getPet_id() {
        return pet_id;
    }

    public void setPet_id(String pet_id) {
        this.pet_id = pet_id;
    }

    public Date getDate() {return date;}

    public void setDate(Date date) {this.date = date;}

    public Time getTime() {return time;}

    public void setTime(Time time) {this.time = time;}

    public int getNumber() {return Number;}
    public void setNumber(int number) {Number = number;}

    @Override
    public String toString() {
        return "Appointment{" +
                "app_id='" + app_id + '\'' +
                ", doctor_id='" + doctor_id + '\'' +
                ", pet_id='" + pet_id + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", Number=" + Number +
                '}';
    }
}
