package lk.ijse.animal_clinic.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class vaccinationsTm {
    private String vaccination_id;
    private String VaccinationName;
    private Date next_dueDate;
}
