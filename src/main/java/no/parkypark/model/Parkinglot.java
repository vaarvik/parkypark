package no.parkypark.model;

import java.util.UUID;

public class Parkinglot {
    private String name;
    private String address;
    private String id;
    private String ownerId;
    private double price;

    public Parkinglot(String name, String address, String ownerId, double price) {
        this.name = name;
        this.address = address;
        this.id = UUID.randomUUID().toString();
        this.ownerId = ownerId;
        this.price = price;
    }

    public Parkinglot() {}

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "Parkinglot{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", id='" + id + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", price=" + price +
                '}';
    }
}
