package org.example.DAO.DAOImpl;

import org.example.DAO.OrderDAO;
import org.example.models.Order;
import org.example.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public void addOrder(Order order) throws SQLException {
        String query = "INSERT INTO \"Order\" (user_id, car_id, order_date, status) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getCarId());
            ps.setTimestamp(3, order.getOrderDate());
            ps.setString(4, order.getStatus());
            ps.executeUpdate();
        }
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) throws SQLException {
        String query = "SELECT o.id, o.user_id, o.car_id, c.make AS car_make, c.model AS car_model, o.order_date, o.status " +
                "FROM \"Order\" o " +
                "JOIN car c ON o.car_id = c.id " +
                "WHERE o.user_id = ?";
        List<Order> orders = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order();
                    order.setId(rs.getInt("id"));
                    order.setUserId(rs.getInt("user_id"));
                    order.setCarId(rs.getInt("car_id"));
                    order.setOrderDate(rs.getTimestamp("order_date"));
                    order.setStatus(rs.getString("status"));

                    order.setCarMake(rs.getString("car_make"));
                    order.setCarModel(rs.getString("car_model"));

                    orders.add(order);
                }
            }
        }
        return orders;
    }

}
