package lk.ijse.animal_clinic.entity;

import java.sql.Time;

public class Doctor {
    private String id;
    private String name;
    private String tel;
    private String address;
    private String email;
    private Time comeInTime;
    private Time outTime;

    public Doctor() {
    }

    public Doctor(String id, String name, String tel, String address, String email, Time comeInTime, Time outTime) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.address = address;
        this.email = email;
        this.comeInTime = comeInTime;
        this.outTime = outTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Time getComeInTime() {
        return comeInTime;
    }

    public void setComeInTime(Time comeInTime) {
        this.comeInTime = comeInTime;
    }

    public Time getOutTime() {
        return outTime;
    }

    public void setOutTime(Time outTime) {
        this.outTime = outTime;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", comeInTime=" + comeInTime +
                ", outTime=" + outTime +
                '}';
    }
}
