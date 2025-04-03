package ru.itis.servises;

import ru.itis.dto.UserDto;
import ru.itis.repositories.UsersRepository;

import java.util.List;

import static ru.itis.dto.UserDto.from;

public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<UserDto> getAll() {
        return from(usersRepository.findAll());
    }
}
