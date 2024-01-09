package lk.ijse.animal_clinic.entity;

import java.sql.Date;

public class Stock {
    private  String stock_id;
    private String category;
    private Date date;

    public Stock() {
    }

    public Stock(String stock_id, String category, Date date) {
        this.stock_id = stock_id;
        this.category = category;
        this.date = date;
    }

    public String getStock_id() {
        return stock_id;
    }

    public void setStock_id(String stock_id) {
        this.stock_id = stock_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
