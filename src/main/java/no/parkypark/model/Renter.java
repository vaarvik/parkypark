package no.parkypark.model;

public class Renter extends User {
    private Car car;

    public Renter(String name, String email, String password, Car car) {
        super(name, email, password);
        this.car = car;
    }
}
