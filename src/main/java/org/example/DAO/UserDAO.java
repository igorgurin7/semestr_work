package org.example.DAO;

import org.example.models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    User getUserById(int id) throws SQLException;

    List<User> getAllUsers() throws SQLException;

    void addUser(User user) throws SQLException;

    void updateUser(User user) throws SQLException;

    void deleteUser(int id) throws SQLException;

    User getUserByEmail(String email) throws SQLException;

    User getUserByUsername(String username) throws SQLException;
}
