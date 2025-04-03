package ru.itis.servises;

import ru.itis.dto.UserDto;

import java.util.List;

public interface UsersService {
    List<UserDto> getAll();
}
