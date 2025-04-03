package ru.itis.models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;

    public enum Role {
        ADMIN, ORGANIZER, CLIENT
    }
}
