package org.example.DAO.DAOImpl;

import org.example.DAO.RoleDAO;
import org.example.models.Role;
import org.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDAOImpl implements RoleDAO {

    public Role getRoleById(int id) throws SQLException {
        String query = "SELECT * FROM Role WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return mapRole(resultSet);
            }
        }
        return null;
    }

    public List<Role> getAllRoles() throws SQLException {
        String query = "SELECT * FROM Role";
        List<Role> roles = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                roles.add(mapRole(resultSet));
            }
        }
        return roles;
    }

    public void addRole(Role role) throws SQLException {
        String query = "INSERT INTO Role (name) VALUES (?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, role.getName());
            statement.executeUpdate();
        }
    }

    private Role mapRole(ResultSet resultSet) throws SQLException {
        Role role = new Role();
        role.setId(resultSet.getInt("id"));
        role.setName(resultSet.getString("name"));
        return role;
    }
}
