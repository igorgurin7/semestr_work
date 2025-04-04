package org.example.models;

import lombok.Data;

import java.sql.Timestamp;


@Data
public class User {

    private int id;
    private String username;
    private String email;
    private String password;
    private int roleId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
