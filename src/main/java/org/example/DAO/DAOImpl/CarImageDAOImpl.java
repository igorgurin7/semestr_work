package org.example.DAO.DAOImpl;

import org.example.DAO.CarImageDAO;
import org.example.models.CarImage;
import org.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarImageDAOImpl implements CarImageDAO {

    public CarImage getCarImageByCarId(int carId) throws SQLException {
        String query = "SELECT ci.* FROM Car c LEFT JOIN CarImage ci ON c.id = ci.car_id WHERE c.id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, carId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return mapCarImage(resultSet);
            }
        }
        return null;
    }

    public List<CarImage> getAllCarImages() throws SQLException {
        String query = "SELECT * FROM CarImage";
        List<CarImage> carImages = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                carImages.add(mapCarImage(resultSet));
            }
        }
        return carImages;
    }

    public void addCarImage(CarImage carImage) throws SQLException {
        String query = "INSERT INTO CarImage (car_id, image_path) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, carImage.getCarId());
            statement.setString(2, carImage.getImagePath());
            statement.executeUpdate();
        }
    }

    private CarImage mapCarImage(ResultSet resultSet) throws SQLException {
        CarImage carImage = new CarImage();
        carImage.setId(resultSet.getInt("id"));
        carImage.setCarId(resultSet.getInt("car_id"));
        carImage.setImagePath(resultSet.getString("image_path"));
        carImage.setCreatedAt(resultSet.getTimestamp("created_at"));
        return carImage;
    }
}
