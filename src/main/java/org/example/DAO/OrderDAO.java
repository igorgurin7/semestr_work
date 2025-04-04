package org.example.DAO;

import org.example.models.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO {

    void addOrder(Order order) throws SQLException;

    List<Order> getOrdersByUserId(int userId) throws SQLException;
}
