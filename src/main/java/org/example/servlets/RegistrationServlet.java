package org.example.servlets;

import org.example.DAO.DAOImpl.UserDAOImpl;
import org.example.DTO.UserDTO;
import org.example.service.UserService;
import org.example.util.ValidatorUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    public UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService(new UserDAOImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int role_id = 2;

        try {
            if (!ValidatorUtil.isInputValid(request, response,userService)) {
                return;
            }
        } catch (SQLException e) {
            request.setAttribute("errorMessage", "Ошибка при обработке запроса: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }

        try {
            UserDTO userDTO = new UserDTO(0,username,email,role_id);
            userService.addUser(userDTO,password);
            response.sendRedirect(request.getContextPath() + "/login");
        } catch (Exception e) {
            log("Ошибка при регистрации пользователя", e);
            request.setAttribute("errorMessage",  e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }
}