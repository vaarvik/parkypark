package no.parkypark.model;

import java.util.Date;

public class Booking {

    private User user;
    private Parkinglot parkinglot;
    private Car car;
    private Date checkIn;
    private Date checkOut;
    private long paymentCardNumber;
    private String paymentOption;
    private String creditCardName;
    private boolean carCharging;
    private int creditCardExpiryMonth;
    private int creditCardExpiryYear;

    public Booking(User user, Parkinglot parkinglot, Car car, Date checkIn, Date checkOut, long paymentCardNumber, String paymentOption, String creditCardName, boolean carCharging, int creditCardExpiryMonth, int creditCardExpiryYear) {

        this.user = user;
        this.parkinglot = parkinglot;
        this.car = car;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.paymentCardNumber = paymentCardNumber;
        this.paymentOption = paymentOption;
        this.creditCardName = creditCardName;
        this.carCharging = carCharging;
        this.creditCardExpiryMonth = creditCardExpiryMonth;
        this.creditCardExpiryYear = creditCardExpiryYear;
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

    public long getPaymentCardNumber() {
        return paymentCardNumber;
    }

    public void setPaymentCardNumber(long paymentCardNumber) {
        this.paymentCardNumber = paymentCardNumber;
    }

    public String getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(String paymentOption) {
        this.paymentOption = paymentOption;
    }

    public String getCreditCardName() {
        return creditCardName;
    }

    public void setCreditCardName(String creditCardName) {
        this.creditCardName = creditCardName;
    }

    public boolean isCarCharging() {
        return carCharging;
    }

    public void setCarCharging(boolean carCharging) {
        this.carCharging = carCharging;
    }

    public int getCreditCardExpiryMonth() {
        return creditCardExpiryMonth;
    }

    public void setCreditCardExpiryMonth(int creditCardExpiryMonth) {
        this.creditCardExpiryMonth = creditCardExpiryMonth;
    }

    public int getCreditCardExpiryYear() {
        return creditCardExpiryYear;
    }

    public void setCreditCardExpiryYear(int creditCardExpiryYear) {
        this.creditCardExpiryYear = creditCardExpiryYear;
    }
}
