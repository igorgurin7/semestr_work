package ru.itis.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class EventCategory {
    private Event event;
    private Category category;
}
