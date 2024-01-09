package lk.ijse.animal_clinic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoctorDto {
    private String id;
    private String name;
    private String tel;
    private String address;
    private String email;
    private Time comeInTime;
    private Time outTime;


}