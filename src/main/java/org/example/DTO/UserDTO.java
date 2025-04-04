package org.example.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {

    private int id;
    private String username;
    private String email;
    private int roleId;
}
