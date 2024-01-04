package lk.ijse.animal_clinic.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ThreatmentDetailsDto {

    private String AppointmentId;
    private String treatementId;
    private double Cost;
    private String Type;
    private Double Total;

}
