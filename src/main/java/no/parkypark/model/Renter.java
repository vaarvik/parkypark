package no.parkypark.model;

import java.util.UUID;

public class Renter extends User {
    private UUID carId;

    public Renter(UUID userId, String name, String email, String password, UUID carId) {
        super(userId, name, email, password);
        this.carId = carId;
    }
}
