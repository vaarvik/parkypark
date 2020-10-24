package no.parkypark.model;

import java.util.UUID;

public class Lender extends User {
    private Parkinglot parkinglot;

    public Lender(UUID userId, String name, String email, String password, Parkinglot parkinglot) {
        super(userId, name, email, password);
        this.parkinglot = parkinglot;
    }

    public Parkinglot getParkinglot() {
        return parkinglot;
    }

    public void setParkinglot(Parkinglot parkinglot) {
        this.parkinglot = parkinglot;
    }
}
