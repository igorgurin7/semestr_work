package org.example.DAO;

import org.example.models.CarImage;

import java.sql.SQLException;
import java.util.List;

public interface CarImageDAO {

    CarImage getCarImageByCarId(int id) throws SQLException;

    List<CarImage> getAllCarImages() throws SQLException;

    void addCarImage(CarImage carImage) throws SQLException;


}
