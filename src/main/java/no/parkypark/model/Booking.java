package no.parkypark.model;

import java.util.Date;
import java.util.UUID;

public class Booking {

    private String userId;
    private UUID parkinglotId;
    private String carLicenceNumber;
    private Date checkIn;
    private Date checkOut;
    private Payment payment;


    public Booking(String userId, UUID parkinglotId, String carLicenceNumber, Date checkIn, Date checkOut, Payment payment) {

        this.userId = userId;
        this.parkinglotId = parkinglotId;
        this.carLicenceNumber = carLicenceNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.payment = payment;
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

    public UUID getParkinglot() {
        return this.parkinglotId;
    }

    public void setParkinglot(UUID parkinglotId) {
        this.parkinglotId = parkinglotId;
    }

    public String getCar() {
        return this.carLicenceNumber;
    }

    public void setCar(String carLicenceNumber) {
        this.carLicenceNumber = carLicenceNumber;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }


}
