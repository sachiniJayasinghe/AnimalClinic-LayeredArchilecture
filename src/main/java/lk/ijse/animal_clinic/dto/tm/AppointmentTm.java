package lk.ijse.animal_clinic.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppointmentTm {
    private String app_id;
    private String doctor_id;
    private String pet_id;
    private Date date;

    private Time time ;
    private int Number;




}
