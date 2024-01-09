package lk.ijse.animal_clinic.entity;

import java.sql.Date;

public class Salary {
    private String salary_id;
    private String emp_id;
    private Date date;
    private String salary_month;
    private Double amount;

    public Salary(String salary_id, String emp_id, Date date, String salary_month, Double amount) {
        this.salary_id = salary_id;
        this.emp_id = emp_id;
        this.date = date;
        this.salary_month = salary_month;
        this.amount = amount;
    }

    public Salary() {
    }

    public String getSalary_id() {
        return salary_id;
    }

    public void setSalary_id(String salary_id) {
        this.salary_id = salary_id;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSalary_month() {
        return salary_month;
    }

    public void setSalary_month(String salary_month) {
        this.salary_month = salary_month;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;

    }

    @Override
    public String toString() {
        return "Salary{" +
                "salary_id='" + salary_id + '\'' +
                ", emp_id='" + emp_id + '\'' +
                ", date=" + date +
                ", salary_month='" + salary_month + '\'' +
                ", amount=" + amount +
                '}';
    }
}
