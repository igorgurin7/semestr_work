package org.example.service;

import org.example.DAO.DAOImpl.CarDAOImpl;
import org.example.DTO.CarDTO;
import org.example.models.Car;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class CarService {

    private final CarDAOImpl carDAO;

    public CarService(CarDAOImpl carDAO) {
        this.carDAO = carDAO;
    }

    public List<CarDTO> getAllCars() throws SQLException {
        return carDAO.getAllCars().stream()
                .map(this::toCarDTO)
                .collect(Collectors.toList());
    }

    public CarDTO getCarById(int id) throws SQLException {
        Car car = carDAO.getCarById(id);
        return car != null ? toCarDTO(car) : null;
    }

    public void addCar(CarDTO carDTO, List<Part> imageParts) throws SQLException, IOException {
        Car car = toCar(carDTO);
        if (imageParts != null && !imageParts.isEmpty()) {
            List<String> imagePaths = saveImages(imageParts);
            car.setImagePaths(imagePaths);
        }
        carDAO.addCar(car, imageParts);
    }

    public void updateCar(CarDTO carDTO, List<Part> imageParts) throws SQLException, IOException {
        Car car = toCar(carDTO);
        if (imageParts == null || imageParts.isEmpty()) {

            CarDTO existingCar = getCarById(carDTO.getId());
            if (existingCar != null) {
                car.setImagePaths(existingCar.getImagePaths());
            }
        } else {
            List<String> imagePaths = saveImages(imageParts);
            car.setImagePaths(imagePaths);
        }
        car.setId(carDTO.getId());
        carDAO.updateCar(car, imageParts);
    }

    public void deleteCar(int id) throws SQLException {
        carDAO.deleteCar(id);
    }

    private CarDTO toCarDTO(Car car) {
        return new CarDTO(car.getId(), car.getModel(), car.getMake(), car.getDescription(),
                car.getPrice(), car.getYear(), car.getImagePaths());
    }

    private Car toCar(CarDTO carDTO) {
        Car car = new Car();
        car.setModel(carDTO.getModel());
        car.setMake(carDTO.getMake());
        car.setDescription(carDTO.getDescription());
        car.setPrice(carDTO.getPrice());
        car.setYear(carDTO.getYear());
        car.setImagePaths(carDTO.getImagePaths());
        return car;
    }

    private List<String> saveImages(List<Part> imageParts) throws IOException {
        return imageParts.stream()
                .map(part -> {
                    try {
                        return saveImageToFileSystem(part);
                    } catch (IOException e) {
                        return null;
                    }
                })
                .filter(path -> path != null)
                .collect(Collectors.toList());
    }

    public void handleGetAllCars(HttpServletRequest req, HttpServletResponse resp, boolean isAdmin) throws SQLException, ServletException, IOException {
        List<CarDTO> cars = getAllCars();
        req.setAttribute("cars", cars);
        req.setAttribute("isAdmin", isAdmin);
        req.getRequestDispatcher("/WEB-INF/views/cars.jsp").forward(req, resp);
    }

    public void handleAddPage(HttpServletRequest req, HttpServletResponse resp, boolean isAdmin) throws IOException, ServletException {
        if (isAdmin) {
            req.getRequestDispatcher("/WEB-INF/views/add-or-update-car.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/access-denied");
        }
    }

    public void handleUpdatePage(HttpServletRequest req, HttpServletResponse resp, boolean isAdmin, String pathInfo) throws SQLException, ServletException, IOException {
        if (isAdmin) {
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length == 3) {
                int carId = Integer.parseInt(pathParts[2]);
                CarDTO car = getCarById(carId);
                if (car != null) {
                    req.setAttribute("car", car);
                    req.getRequestDispatcher("/WEB-INF/views/add-or-update-car.jsp").forward(req, resp);
                } else {
                    throw new IllegalArgumentException("Машина не найдена");
                }
            } else {
                throw new IllegalArgumentException("Неверный формат URL");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/access-denied");
        }
    }

    public void handleCarDetails(HttpServletRequest req, HttpServletResponse resp, boolean isAdmin, String pathInfo) throws SQLException, ServletException, IOException {
        String[] pathParts = pathInfo.split("/");
        if (pathParts.length == 2) {
            int carId = Integer.parseInt(pathParts[1]);
            CarDTO car = getCarById(carId);
            if (car != null) {
                req.setAttribute("car", car);
                req.setAttribute("isAdmin", isAdmin);
                req.getRequestDispatcher("/WEB-INF/views/car-details.jsp").forward(req, resp);
            } else {
                throw new IllegalArgumentException("Машина не найдена");
            }
        } else {
            throw new IllegalArgumentException("Неверный формат URL");
        }
    }

    public void handleAddOrUpdate(HttpServletRequest req, HttpServletResponse resp, String action) throws IOException, SQLException, ServletException {
        String model = req.getParameter("model");
        String make = req.getParameter("make");
        String description = req.getParameter("description");
        double price = Double.parseDouble(req.getParameter("price"));
        int year = Integer.parseInt(req.getParameter("year"));

        List<Part> imageParts = req.getParts().stream()
                .filter(part -> "image".equals(part.getName()))
                .collect(Collectors.toList());

        CarDTO carDTO = new CarDTO(
                "add".equals(action) ? 0 : Integer.parseInt(req.getParameter("id")),
                model,
                make,
                description,
                price,
                year,
                null
        );

        if ("add".equals(action)) {
            addCar(carDTO, imageParts);
        } else {
            updateCar(carDTO, imageParts);
        }

        resp.sendRedirect(req.getContextPath() + "/cars");
    }

    public void handleDelete(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int carId = Integer.parseInt(req.getParameter("id"));
        deleteCar(carId);

        if ("XMLHttpRequest".equals(req.getHeader("X-Requested-With"))) {
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.sendRedirect(req.getContextPath() + "/cars");
        }
    }

    private String saveImageToFileSystem(Part imagePart) throws IOException {
        String imageName = imagePart.getSubmittedFileName();
        String uploadPath = "C:\\uploaded_images";
        String imagePath = "/uploaded_images/" + imageName;

        File dir = new File(uploadPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        imagePart.write(uploadPath + File.separator + imageName);
        return imagePath;
    }
}