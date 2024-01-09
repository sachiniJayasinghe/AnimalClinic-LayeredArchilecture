package lk.ijse.animal_clinic.entity;

public class ThreatmentDetails {
    private String AppointmentId;
    private String treatementId;
    private double Cost;
    private String Type;
    private Double Total;
    public ThreatmentDetails(){}

    public ThreatmentDetails(String appointmentId, String treatementId, double cost, String type, Double total) {
        AppointmentId = appointmentId;
        this.treatementId = treatementId;
        Cost = cost;
        Type = type;
        Total = total;
    }

    public String getAppointmentId() {
        return AppointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        AppointmentId = appointmentId;
    }

    public String getTreatementId() {
        return treatementId;
    }

    public void setTreatementId(String treatementId) {
        this.treatementId = treatementId;
    }

    public double getCost() {
        return Cost;
    }

    public void setCost(double cost) {
        Cost = cost;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double total) {
        Total = total;
    }

    @Override
    public String toString() {
        return "ThreatmentDetails{" +
                "AppointmentId='" + AppointmentId + '\'' +
                ", treatementId='" + treatementId + '\'' +
                ", Cost=" + Cost +
                ", Type='" + Type + '\'' +
                ", Total=" + Total +
                '}';
    }
}
