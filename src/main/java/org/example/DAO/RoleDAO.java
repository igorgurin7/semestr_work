package org.example.DAO;

import org.example.models.Role;

import java.sql.SQLException;
import java.util.List;

public interface RoleDAO {

    Role getRoleById(int id) throws SQLException;

    List<Role> getAllRoles() throws SQLException;

    void addRole(Role role) throws SQLException;
}
