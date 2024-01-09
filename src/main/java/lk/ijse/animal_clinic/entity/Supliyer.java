package lk.ijse.animal_clinic.entity;

public class Supliyer {
    private  String supliyer_id ;
    private String stock_id ;
    private String supliyer_name ;
    private String  tel ;
    private String e_mail;
    private String address;

    public Supliyer(){}

    public Supliyer(String supliyer_id, String stock_id, String supliyer_name, String tel, String e_mail, String address) {
        this.supliyer_id = supliyer_id;
        this.stock_id = stock_id;
        this.supliyer_name = supliyer_name;
        this.tel = tel;
        this.e_mail = e_mail;
        this.address = address;
    }

    public String getSupliyer_id() {
        return supliyer_id;
    }

    public void setSupliyer_id(String supliyer_id) {
        this.supliyer_id = supliyer_id;
    }

    public String getStock_id() {
        return stock_id;
    }

    public void setStock_id(String stock_id) {
        this.stock_id = stock_id;
    }

    public String getSupliyer_name() {
        return supliyer_name;
    }

    public void setSupliyer_name(String supliyer_name) {
        this.supliyer_name = supliyer_name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Supliyer{" +
                "supliyer_id='" + supliyer_id + '\'' +
                ", stock_id='" + stock_id + '\'' +
                ", supliyer_name='" + supliyer_name + '\'' +
                ", tel='" + tel + '\'' +
                ", e_mail='" + e_mail + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
