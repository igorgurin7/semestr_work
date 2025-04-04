package org.example.servlets;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.example.DAO.DAOImpl.OrderDAOImpl;
import org.example.DAO.DAOImpl.UserDAOImpl;
import org.example.DTO.UserDTO;
import org.example.models.User;
import org.example.models.Order;
import org.example.service.OrderService;
import org.example.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private UserService userService;
    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        userService = new UserService(new UserDAOImpl());
        orderService = new OrderService(new OrderDAOImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            List<Order> orders = orderService.getOrdersByUserId(user.getId());
            request.setAttribute("user", user);
            request.setAttribute("orders", orders);

            String orderMessage = (String) request.getSession().getAttribute("orderMessage");
            if (orderMessage != null) {
                request.setAttribute("orderMessage", orderMessage);
                request.getSession().removeAttribute("orderMessage");
            }

            request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ошибка при получении данных пользователя и заказов");
        }
    }
}
