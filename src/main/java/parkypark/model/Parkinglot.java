package parkypark.model;

public class Parkinglot {
    private String name;

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

    private String address;

    public Parkinglot(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
