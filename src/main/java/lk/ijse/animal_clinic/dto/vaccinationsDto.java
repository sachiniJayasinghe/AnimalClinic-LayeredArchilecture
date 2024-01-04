package lk.ijse.animal_clinic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class vaccinationsDto {
    private String vaccination_id;
    private String VaccinationName;
    private Date next_dueDate;
}
