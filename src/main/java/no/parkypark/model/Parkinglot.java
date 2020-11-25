package no.parkypark.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import no.parkypark.utils.JsonDateDeserialize;
import no.parkypark.utils.JsonDateSerializer;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Parkinglot {
    private String name;
    private String address;
    private String id;
    private String ownerId;
    private String image;
    private String description;
    private double price;

    @JsonDeserialize(using = JsonDateDeserialize.class)
    @JsonSerialize(using = JsonDateSerializer.class)
    private LocalDateTime checkin;

    @JsonDeserialize(using = JsonDateDeserialize.class)
    @JsonSerialize(using = JsonDateSerializer.class)
    private LocalDateTime checkout;

    public Parkinglot(
            String name,
            String address,
            String ownerId,
            double price,
            String image,
            String description,
            LocalDateTime checkin,
            LocalDateTime checkout
    ) {
        this.name = name;
        this.address = address;
        this.id = UUID.randomUUID().toString();
        this.ownerId = ownerId;
        this.image = image;
        this.description = description;
        this.price = price;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    //used by front-end and Jackson
    public Parkinglot() {
        this.id = UUID.randomUUID().toString();
    }

    public void update(Parkinglot parkinglot) {
        this.name = parkinglot.getName();
        this.address = parkinglot.getAddress();
        this.price = parkinglot.getPrice();
        this.checkout = parkinglot.getCheckout();
        this.checkin = parkinglot.getCheckin();
        this.description = parkinglot.getDescription();
        this.image = parkinglot.getImage();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalDateTime checkin) {
        this.checkin = checkin;
    }

    public LocalDateTime getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDateTime checkout) {
        this.checkout = checkout;
    }

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
                ", image=" + image +
                ", description=" + description +
                ", checkin=" + checkin +
                ", checkout=" + checkout +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parkinglot that = (Parkinglot) o;
        return Double.compare(that.price, price) == 0 &&
                Objects.equals(name, that.name) &&
                Objects.equals(address, that.address) &&
                Objects.equals(id, that.id) &&
                Objects.equals(ownerId, that.ownerId) &&
                Objects.equals(image, that.image) &&
                Objects.equals(description, that.description) &&
                Objects.equals(checkin, that.checkin) &&
                Objects.equals(checkout, that.checkout);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, id, ownerId, price, image, description, checkin, checkout);
    }
}
