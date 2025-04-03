package ru.itis.servises;

import ru.itis.dto.SignUpForm;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;

import java.util.Locale;

public class SignUpServiceImpl implements SignUpService {
    private final UsersRepository usersRepository;

    public SignUpServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public void signUp(SignUpForm form) {
        User user = User.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .email(form.getEmail().toLowerCase(Locale.ROOT))
                .password(form.getPassword())
                .build();

        usersRepository.save(user);
    }
}
