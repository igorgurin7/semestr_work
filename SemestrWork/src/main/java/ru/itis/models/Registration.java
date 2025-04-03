package ru.itis.models;

public class Registration {
    private Long id;
    private Long user_id;
    private Long event_id;
    private PaymentStatus paymentStatus;

    public enum PaymentStatus {
        PENDING, PAID, CANCELLED
    }
}
