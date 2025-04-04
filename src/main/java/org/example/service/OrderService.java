package org.example.service;

import org.example.DAO.OrderDAO;
import org.example.DAO.DAOImpl.OrderDAOImpl;
import org.example.models.Order;

import java.sql.SQLException;
import java.util.List;

public class OrderService {

    private OrderDAO orderDAO;

    public OrderService(OrderDAOImpl orderDAO) {
        this.orderDAO = orderDAO;
    }

    public void createOrder(Order order) throws SQLException {
        orderDAO.addOrder(order);
    }

    public List<Order> getOrdersByUserId(int userId) throws SQLException {
        return orderDAO.getOrdersByUserId(userId);
    }
}
