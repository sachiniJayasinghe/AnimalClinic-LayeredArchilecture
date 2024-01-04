package lk.ijse.animal_clinic.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SalaryTm {
    private String salary_id;
    private String emp_id;
    private Date date;
    private String salary_month;
    private Double amount;

}
