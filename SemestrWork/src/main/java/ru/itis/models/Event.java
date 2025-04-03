package ru.itis.models;

import java.time.LocalDateTime;

public class Event {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime date;
    private User organizer;
    private double price;
}
