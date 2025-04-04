package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private int id;
    private int userId;
    private int carId;
    private Timestamp orderDate;
    private String status;
    private String carMake;
    private String carModel;
}
