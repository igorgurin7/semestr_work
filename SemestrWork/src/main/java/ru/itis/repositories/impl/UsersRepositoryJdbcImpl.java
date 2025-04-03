package ru.itis.repositories.impl;

import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    //language=sql
    private static final String SQL_SELECT_BY_ID = "select * from users where user_id = ?";
    //language=SQL
    private static final String SQL_INSERT = "insert into users(first_name, last_name, email, password) " +
            "values (?, ?, ?, ?)";
    //language=sql
    private static final String SQL_UPDATE = "update users set" +
            " first_name = ?, last_name = ?, email = ?, password = ? where id = ?;";
    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from users order by id limit ? offset ?;";

    private final DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement (SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString (1, user.getFirstName());
            statement.setString (2, user.getLastName());
            statement.setString (3, user.getEmail());
            statement.setString (4, user.getPassword());
            int affectedRows = statement.executeUpdate();
            if (affectedRows != 1) {
                throw new SQLException ("Can't insert student");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong( "user_id")) ;
            } else {
                throw new SQLException ("Can't get id");
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    User user = new User(resultSet.getLong("user_id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("email"),
                            resultSet.getString("password"));
                    users.add(user);
                }
                return users;
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void update(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement (SQL_UPDATE))  {
            statement.setString (1, user.getFirstName());
            statement.setString (2, user.getLastName());
            statement.setString (3, user.getEmail());
            statement.setString (4, user.getPassword());
            statement.setLong (5, user.getId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows != 1) {
                throw new SQLException ("Can't update student");
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Optional<User> findById(Long id) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            statement.setLong( 1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(new User(resultSet.getLong("user_id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("email"),
                            resultSet.getString("password")));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}