package no.parkypark.model;

public class Lender extends User {
    private Parkinglot parkinglot;

    public Lender(String name, String email, String password, Parkinglot parkinglot) {
        super(name, email, password);
        this.parkinglot = parkinglot;
    }

    public Parkinglot getParkinglot() {
        return parkinglot;
    }

    public void setParkinglot(Parkinglot parkinglot) {
        this.parkinglot = parkinglot;
    }
}
