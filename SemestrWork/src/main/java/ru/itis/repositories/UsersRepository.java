package ru.itis.repositories;

import ru.itis.models.User;
import java.util.List;
import java.util.Optional;

public interface UsersRepository {

    void save(User user);

    List<User> findAll();

    void update(User user);

    void delete(String id);

    Optional<User> findById(Long id);
}
