package no.parkypark.model;

import java.util.UUID;

public class Parkinglot {
    private String name;
    private String address;
    private String id;
    private String userId;
    private double price;

    public Parkinglot(String name, String address, String userId, double price) {
        this.name = name;
        this.address = address;
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.price = price;
    }

    public Parkinglot(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Parkinglot{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", price=" + price +
                '}';
    }
}
