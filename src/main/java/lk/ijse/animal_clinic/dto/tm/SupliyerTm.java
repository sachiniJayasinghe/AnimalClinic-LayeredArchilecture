package lk.ijse.animal_clinic.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SupliyerTm {
    private  String supliyer_id ;
    private String stock_id ;
    private String supliyer_name ;
    private String  tel ;
    private String e_mail;
    private String address;

}
