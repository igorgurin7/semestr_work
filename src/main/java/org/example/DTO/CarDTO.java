package org.example.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CarDTO {
    private int id;
    private String model;
    private String make;
    private String description;
    private double price;
    private int year;
    private List<String> imagePaths;
}
