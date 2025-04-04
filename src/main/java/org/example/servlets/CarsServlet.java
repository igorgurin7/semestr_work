package org.example.servlets;

import org.example.DAO.DAOImpl.CarDAOImpl;
import org.example.DTO.CarDTO;
import org.example.DTO.UserDTO;
import org.example.service.CarService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/cars/*")
@MultipartConfig(location = "C:\\uploaded_images", fileSizeThreshold = 1024, maxFileSize = 10485760, maxRequestSize = 20971520)
public class CarsServlet extends HttpServlet {

    private CarService carService;

    @Override
    public void init() throws ServletException {
        this.carService = new CarService(new CarDAOImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        HttpSession session = req.getSession(false);
        boolean isAdmin = isAdmin(session);

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                carService.handleGetAllCars(req, resp, isAdmin);
            } else if (pathInfo.equals("/add")) {
                carService.handleAddPage(req, resp, isAdmin);
            } else if (pathInfo.startsWith("/update/")) {
                carService.handleUpdatePage(req, resp, isAdmin, pathInfo);
            } else {
                carService.handleCarDetails(req, resp, isAdmin, pathInfo);
            }
        } catch (Exception e) {
            handleError(req, resp, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (!isAdmin(session)) {
            resp.sendRedirect(req.getContextPath() + "/access-denied");
            return;
        }

        String action = req.getParameter("action");

        try {
            if ("add".equals(action) || "update".equals(action)) {
                carService.handleAddOrUpdate(req, resp, action);
            } else if ("delete".equals(action)) {
                carService.handleDelete(req, resp);
            } else {
                throw new IllegalArgumentException("Неверное действие");
            }
        } catch (Exception e) {
            handleError(req, resp, e);
        }
    }

    private void handleError(HttpServletRequest req, HttpServletResponse resp, Exception e) throws ServletException, IOException {
        req.setAttribute("errorMessage", e.getMessage() != null ? e.getMessage() : "Произошла ошибка");
        req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
    }

    private boolean isAdmin(HttpSession session) {
        if (session == null || session.getAttribute("user") == null) {
            return false;
        }
        UserDTO user = (UserDTO) session.getAttribute("user");
        return user.getRoleId() == 1;
    }
}