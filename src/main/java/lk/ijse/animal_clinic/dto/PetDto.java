package lk.ijse.animal_clinic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PetDto {
    private String pet_id;
    private String name;
    private String pet_type;
    private String age;
    private String cus_id;
}