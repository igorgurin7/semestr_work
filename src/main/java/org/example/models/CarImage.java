package org.example.models;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CarImage {
    private int id;
    private int carId;
    private String imagePath;
    private Timestamp createdAt;
}
