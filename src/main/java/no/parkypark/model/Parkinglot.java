package no.parkypark.model;

public class Parkinglot {
    private String name;
    private String address;
    private String id;
    private int userId;
    private double price;

    public Parkinglot(String name, String address, String id, int userId, double price) {
        this.name = name;
        this.address = address;
        this.id = id;
        this.userId = userId;
        this.price = price;
    }

    public Parkinglot(){

    }

    public Parkinglot(String name, String address, String id) {
        this.name = name;
        this.address = address;
        this.id = id;
    }

    public Parkinglot(String name, String address) {
        this.name = name;
        this.address = address;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Parkinglot{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
