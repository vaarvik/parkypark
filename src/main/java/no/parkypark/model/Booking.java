package no.parkypark.model;

import java.util.Date;

public class Booking {

    private User user;
    private Parkinglot parkinglot;
    private Car car;
    private Date checkIn;
    private Date checkOut;
    private Payment payment;


    public Booking(User user, Parkinglot parkinglot, Car car, Date checkIn, Date checkOut, Payment payment) {

        this.user = user;
        this.parkinglot = parkinglot;
        this.car = car;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Parkinglot getParkinglot() {
        return parkinglot;
    }

    public void setParkinglot(Parkinglot parkinglot) {
        this.parkinglot = parkinglot;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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
