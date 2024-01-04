package lk.ijse.animal_clinic.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    private String id;
    private String password;
    private String name;

    private String address;
    private String email;

    private byte[] image;
}

