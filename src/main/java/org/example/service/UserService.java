package org.example.service;

import org.example.DAO.DAOImpl.UserDAOImpl;
import org.example.DTO.UserDTO;
import org.example.models.User;
import org.example.util.PasswordUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    private final UserDAOImpl userDAO;

    public UserService(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    public List<UserDTO> getAllUsers() throws SQLException {
        return userDAO.getAllUsers().stream().map(this::toUserDTO).collect(Collectors.toList());
    }

    public UserDTO getUserById(int id) throws SQLException {
        User user = userDAO.getUserById(id);

        if (user != null) {
            return toUserDTO(user);
        }
        return null;
    }

    public void addUser(UserDTO userDTO, String password) throws SQLException {
        String hashedPassword = PasswordUtil.hashPassword(password);

        User user = toUser(userDTO);
        user.setPassword(hashedPassword);

        userDAO.addUser(user);
    }

    public UserDTO getUserByUsername(String username) throws SQLException{
        User user = userDAO.getUserByUsername(username);

        if (user != null) {
            return toUserDTO(user);
        }
        return null;
    }

    public UserDTO getUserByEmail(String email) throws SQLException{
        User user = userDAO.getUserByUsername(email);

        if (user != null) {
            return toUserDTO(user);
        }
        return null;
    }

    public void deleteUser(int id) throws SQLException{
        userDAO.deleteUser(id);
    }

    public void updateUser(int id, String username, String email, int roleId) throws SQLException {
        User user = userDAO.getUserById(id);
        if (user != null) {
            user.setUsername(username);
            user.setEmail(email);
            user.setRoleId(roleId);
            userDAO.updateUser(user);
        }
    }

    private UserDTO toUserDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getRoleId());
    }

    private User toUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setRoleId(userDTO.getRoleId());
        return user;
    }
}
