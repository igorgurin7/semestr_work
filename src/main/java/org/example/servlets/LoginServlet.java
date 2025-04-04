package org.example.servlets;

import org.example.DAO.DAOImpl.UserDAOImpl;
import org.example.DTO.UserDTO;
import org.example.service.AuthenticationService;
import org.example.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    public AuthenticationService authenticationService;
    public UserService userService;

    @Override
    public void init() throws ServletException {
        authenticationService = new AuthenticationService(new UserDAOImpl());
        userService = new UserService(new UserDAOImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            if (authenticationService.authenticate(username, password)) {
                UserDTO userDTO = userService.getUserByUsername(username);
                HttpSession session = request.getSession();
                session.setAttribute("user", userDTO);
                response.sendRedirect(request.getContextPath() + "/cars");
            } else {

                request.setAttribute("errorMessage", "Неверные имя пользователя или пароль.");
                request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            log("Ошибка аутентификации", e);
            request.setAttribute("errorMessage", "Произошла ошибка при входе. Попробуйте позже.");
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }
}
