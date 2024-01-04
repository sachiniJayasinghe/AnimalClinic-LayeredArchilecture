package lk.ijse.animal_clinic.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ThreatmentDetailTm {
    private String AppointmentId;
    private String treatementId;
    private double Cost;
    private String Type;
    private Double Total;
}
