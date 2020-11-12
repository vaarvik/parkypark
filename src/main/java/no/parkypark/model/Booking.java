package no.parkypark.model;

import java.util.Date;
import java.util.UUID;

public class Booking {

    private String id;
    private String userId;
    private String parkinglotId;
    private String carLicenceNumber;
    private Date checkIn;
    private Date checkOut;
    private Payment payment;


    public Booking(String userId, String parkinglotId, String carLicenceNumber, Date checkIn, Date checkOut, Payment payment) {
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
