package no.parkypark.model;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import no.parkypark.utils.JsonDateDeserialize;
import no.parkypark.utils.JsonDateSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Booking {
    private final String id;
    private String userId;
    private String parkinglotId;
    private String carLicenceNumber;
    private Payment payment;

    @JsonDeserialize(using = JsonDateDeserialize.class)
    @JsonSerialize(using = JsonDateSerializer.class)
    private LocalDateTime checkIn;

    @JsonDeserialize(using = JsonDateDeserialize.class)
    @JsonSerialize(using = JsonDateSerializer.class)
    private LocalDateTime checkOut;

    public Booking(String userId, String parkinglotId, String carLicenceNumber, LocalDateTime checkIn, LocalDateTime checkOut, Payment payment) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.parkinglotId = parkinglotId;
        this.carLicenceNumber = carLicenceNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.payment = payment;
    }

    //used by front-end and bodyAsClass
    public Booking() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getParkinglotId() {
        return this.parkinglotId;
    }

    public void setParkinglotId(String parkinglotId) {
        this.parkinglotId = parkinglotId;
    }

    public String getCarLicenceNumber() {
        return this.carLicenceNumber;
    }

    public void setCarLicenceNumber(String carLicenceNumber) {
        this.carLicenceNumber = carLicenceNumber;
    }

    public LocalDateTime getCheckIn() {
        return this.checkIn;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
        System.out.println(checkIn);
    }

    public LocalDateTime getCheckOut() {
        return this.checkOut;
    }

    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id) &&
                Objects.equals(userId, booking.userId) &&
                Objects.equals(parkinglotId, booking.parkinglotId) &&
                Objects.equals(carLicenceNumber, booking.carLicenceNumber) &&
                Objects.equals(checkIn, booking.checkIn) &&
                Objects.equals(checkOut, booking.checkOut) &&
                Objects.equals(payment, booking.payment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, parkinglotId, carLicenceNumber, checkIn, checkOut, payment);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", parkinglotId='" + parkinglotId + '\'' +
                ", carLicenceNumber='" + carLicenceNumber + '\'' +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", payment=" + payment +
                '}';
    }
}
