package lk.ijse.animal_clinic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StockDto {
    private  String stock_id;
    private String category;
    private  Date date;
}
