package lk.ijse.animal_clinic.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DoctorTm {
    private String id;
    private String name;
    private String tel;
    private String email;
    private String address;
    private Time comeInTime;

    private Time outTime;

}
