package lk.ijse.animal_clinic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TreatementDto {

    private String treatement_id;
    private String vaccination_id;
    private String type;
    private double cost;
    private Date date;

}
