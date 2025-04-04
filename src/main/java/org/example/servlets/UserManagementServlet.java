package org.example.servlets;

import org.example.DAO.DAOImpl.UserDAOImpl;
import org.example.DTO.UserDTO;
import org.example.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/users")
public class UserManagementServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService(new UserDAOImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("edit".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                UserDTO user = userService.getUserById(id);
                request.setAttribute("user", user);
                request.getRequestDispatcher("/WEB-INF/views/editUser.jsp").forward(request, response);
            } catch (SQLException | NumberFormatException e) {
                throw new ServletException(e);
            }
        } else {
            try {
                List<UserDTO> users = userService.getAllUsers();
                request.setAttribute("users", users);
                request.getRequestDispatcher("/WEB-INF/views/userManagement.jsp").forward(request, response);
            } catch (SQLException e) {
                throw new ServletException(e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                UserDTO userDTO = userService.getUserById(id);
                if (userDTO != null && userDTO.getRoleId() == 1) {
                    response.setContentType("application/json");
                    response.getWriter().write("{\"status\":\"error\", \"message\":\"Нельзя удалить администратора\"}");
                    return;
                }
                userService.deleteUser(id);
            } catch (SQLException | NumberFormatException e) {
                throw new ServletException(e);
            }
            if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
                response.setContentType("application/json");
                response.getWriter().write("{\"status\":\"success\"}");
            } else {
                response.sendRedirect(request.getContextPath() + "/admin/users");
            }
        } else if ("update".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                String username = request.getParameter("username");
                String email = request.getParameter("email");
                int roleId = Integer.parseInt(request.getParameter("roleId"));
                userService.updateUser(id, username, email, roleId);
            } catch (SQLException | NumberFormatException e) {
                throw new ServletException(e);
            }
            response.sendRedirect(request.getContextPath() + "/admin/users");
        }
    }
}
