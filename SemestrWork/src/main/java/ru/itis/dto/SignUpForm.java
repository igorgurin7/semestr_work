package ru.itis.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class SignUpForm {
    private String firstName;
    private String lastName;
    private String password;
    private String email;
}
