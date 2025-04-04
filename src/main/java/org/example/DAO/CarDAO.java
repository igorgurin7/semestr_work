package org.example.DAO;

import org.example.models.Car;

import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface CarDAO {

    Car getCarById(int id) throws SQLException;

    List<Car> getAllCars() throws SQLException;

    void addCar(Car car, List<Part> imageParts) throws SQLException, IOException;

    void deleteCar(int id) throws SQLException;

    void updateCar(Car car,List<Part> imageParts) throws SQLException, IOException;
}
