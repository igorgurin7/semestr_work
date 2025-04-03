package ru.itis.models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class Category {
    private Long id;
    private String name;
}
