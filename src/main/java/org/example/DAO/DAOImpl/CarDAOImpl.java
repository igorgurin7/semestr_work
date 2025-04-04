package org.example.DAO.DAOImpl;

import org.example.DAO.CarDAO;
import org.example.models.Car;
import org.example.util.DatabaseConnection;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class CarDAOImpl implements CarDAO {

    @Override
    public Car getCarById(int id) throws SQLException {
        final String query = "SELECT c.*, ci.image_path FROM Car c "
                + "LEFT JOIN car_image ci ON c.id = ci.car_id "
                + "WHERE c.id = ?";

        Car car = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    if (car == null) {
                        car = mapCar(resultSet);
                    }
                    car.getImagePaths().add(resultSet.getString("image_path"));
                }
            }
        }
        return car;
    }

    @Override
    public List<Car> getAllCars() throws SQLException {
        final String query = "SELECT c.*, ci.image_path FROM Car c LEFT JOIN car_image ci ON c.id = ci.car_id";
        Map<Integer, Car> carsMap = new HashMap<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int carId = resultSet.getInt("id");
                carsMap.computeIfAbsent(carId, id -> {
                            try {
                                return mapCar(resultSet);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .getImagePaths()
                        .add(Optional.ofNullable(resultSet.getString("image_path")).orElse(""));
            }
        }
        return new ArrayList<>(carsMap.values());
    }

    @Override
    public void addCar(Car car, List<Part> imageParts) throws SQLException, IOException {
        final String query = "INSERT INTO Car (make, model, year, price, description) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            setCarParams(statement, car);
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    car.setId(generatedKeys.getInt(1));
                    saveCarImages(connection, car.getId(), car.getImagePaths());
                }
            }
        }
    }

    @Override
    public void updateCar(Car car, List<Part> imageParts) throws SQLException, IOException {
        final String query = "UPDATE Car SET make = ?, model = ?, year = ?, price = ?, description = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            setCarParams(statement, car);
            statement.setInt(6, car.getId());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Failed to update the car, no rows affected.");
            }

            if (imageParts != null && !imageParts.isEmpty()) {
                updateCarImages(connection, car.getId(), imageParts);
            }
        }
    }

    @Override
    public void deleteCar(int id) throws SQLException {
        final String query = "DELETE FROM Car WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            statement.executeUpdate();
            deleteCarImagesFromDisk(id);
        }
    }

    private Car mapCar(ResultSet resultSet) throws SQLException {
        return new Car(
                resultSet.getInt("id"),
                resultSet.getString("make"),
                resultSet.getString("model"),
                resultSet.getInt("year"),
                resultSet.getDouble("price"),
                resultSet.getString("description"),
                resultSet.getTimestamp("created_at"),
                new ArrayList<>()
        );
    }

    private void setCarParams(PreparedStatement statement, Car car) throws SQLException {
        statement.setString(1, car.getMake());
        statement.setString(2, car.getModel());
        statement.setInt(3, car.getYear());
        statement.setDouble(4, car.getPrice());
        statement.setString(5, car.getDescription());
    }

    private String saveImage(Part imagePart, String uploadDir) throws IOException {
        String imageName = UUID.randomUUID() + ".jpg";
        File imageFile = new File(uploadDir, imageName);
        imageFile.getParentFile().mkdirs();
        imagePart.write(imageFile.getAbsolutePath());
        return "uploaded_images/" + imageName;
    }

    private void saveCarImages(Connection connection, int carId, List<String> imagePaths) throws SQLException {
        final String query = "INSERT INTO car_image (car_id, image_path) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            for (String path : imagePaths) {
                statement.setInt(1, carId);
                statement.setString(2, path);
                statement.addBatch();
            }
            statement.executeBatch();
        }
    }

    private void deleteCarImagesFromDisk(int carId) throws SQLException {
        final String query = "SELECT image_path FROM car_image WHERE car_id = ?";
        final String uploadDir = "C:\\uploaded_images";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, carId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    File imageFile = new File(uploadDir, resultSet.getString("image_path").replace("uploaded_images/", ""));
                }
            }
        }
    }

    private void updateCarImages(Connection connection, int carId, List<Part> imageParts) throws SQLException, IOException {
        deleteCarImagesFromDisk(carId);
        List<String> imagePaths = new ArrayList<>();
        for (Part part : imageParts) {
            imagePaths.add(saveImage(part, "C:\\uploaded_images"));
        }
        saveCarImages(connection, carId, imagePaths);
    }
}