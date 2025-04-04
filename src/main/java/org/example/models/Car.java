package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private int id;
    private String make;
    private String model;
    private int year;
    private double price;
    private String description;
    private Timestamp createdAt;
    private List<String> imagePaths;
}
