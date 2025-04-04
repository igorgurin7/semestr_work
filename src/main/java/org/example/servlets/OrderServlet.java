package org.example.servlets;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.example.DAO.DAOImpl.OrderDAOImpl;
import org.example.models.Order;
import org.example.service.OrderService;
import org.example.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/orders")
public class OrderServlet extends HttpServlet {

    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        this.orderService = new OrderService(new OrderDAOImpl());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int carId = Integer.parseInt(req.getParameter("carId"));
            int userId = Integer.parseInt(req.getParameter("userId"));

            Order order = new Order();
            order.setCarId(carId);
            order.setUserId(userId);
            order.setOrderDate(new java.sql.Timestamp(System.currentTimeMillis()));
            order.setStatus("В процессе");

            orderService.createOrder(order);

            req.getSession().setAttribute("orderMessage", "Машина успешно оформлена на заказ!");

            resp.sendRedirect(req.getContextPath() + "/profile");
        } catch (Exception e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
    }
}
