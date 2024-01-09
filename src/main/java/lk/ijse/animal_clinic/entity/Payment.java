package lk.ijse.animal_clinic.entity;

public class Payment {
    private String p_id ;
    private  String  app_id;
    private Double amount;

    public Payment() {
    }

    public Payment(String p_id, String app_id, Double amount) {
        this.p_id = p_id;
        this.app_id = app_id;
        this.amount = amount;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "p_id='" + p_id + '\'' +
                ", app_id='" + app_id + '\'' +
                ", amount=" + amount +
                '}';
    }
}
