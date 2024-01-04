package lk.ijse.animal_clinic.dto;

import java.sql.Date;
import java.sql.Time;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppointmentDto {
    private String app_id;
    private String doctor_id;
    private String pet_id;
    private Date date;
    private Time time ;

    private int Number;



}
