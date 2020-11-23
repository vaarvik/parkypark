package no.parkypark.model;

import java.util.UUID;

public class Renter extends User {

    public Renter(UUID userId, String name, String email, String password) {
        super(userId, name, email, password);
    }
}
