package org.example.service;

import org.example.DAO.DAOImpl.UserDAOImpl;
import org.example.models.User;
import org.example.util.PasswordUtil;

import java.sql.SQLException;

public class AuthenticationService {

    private final UserDAOImpl userDAO;

    public AuthenticationService(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    public boolean authenticate(String username, String password) throws SQLException {
        User user = userDAO.getUserByUsername(username);

        if (user != null) {
            return PasswordUtil.checkPassword(password, user.getPassword());
        }

        return false;
    }
}
