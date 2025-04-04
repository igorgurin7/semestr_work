package org.example.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class OrderDTO {
    private int id;
    private int userId;
    private int carId;
    private String carModel;
    private Timestamp orderDate;
    private String status;
}
