package parkypark.model;

import no.parkypark.model.Parkinglot;
import no.parkypark.model.User;

import java.math.BigInteger;
import java.util.Date;

public class Booking{

    private int licencePlate;
    private User user;
    private Parkinglot parkinglot;
    private Date checkIn;
    private Date checkOut;
    private long creditCardNumber;
    private String creditCardName;
    private int creditCardExpiryMonth;
    private int creditCardExpiryYear;

    public Booking(int licencePlate, User user, Parkinglot parkinglot, Date checkIn, Date checkOut, long creditCardNumber, String creditCardName, int creditCardExpiryMonth, int creditCardExpiryYear) {
        this.licencePlate = licencePlate;
        this.user = user;
        this.parkinglot = parkinglot;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.creditCardNumber = creditCardNumber;
        this.creditCardName = creditCardName;
        this.creditCardExpiryMonth = creditCardExpiryMonth;
        this.creditCardExpiryYear = creditCardExpiryYear;
    }

    public Parkinglot getParkinglot() {
        return parkinglot;
    }

    public void setParkinglot(Parkinglot parkinglot) {
        this.parkinglot = parkinglot;
    }

    public int getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(int licencePlate) {
        this.licencePlate = licencePlate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public long getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(long creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardName() {
        return creditCardName;
    }

    public void setCreditCardName(String creditCardName) {
        this.creditCardName = creditCardName;
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

