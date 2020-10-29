package no.parkypark.model;

public class Payment {

    private long paymentCardNumber;
    private String paymentOption;
    private String creditCardName;
    private boolean carCharging;
    private int creditCardExpiryMonth;
    private int creditCardExpiryYear;


    public Payment(long paymentCardNumber, String paymentOption, String creditCardName, boolean carCharging, int creditCardExpiryMonth, int creditCardExpiryYear) {
        this.paymentCardNumber = paymentCardNumber;
        this.paymentOption = paymentOption;
        this.creditCardName = creditCardName;
        this.carCharging = carCharging;
        this.creditCardExpiryMonth = creditCardExpiryMonth;
        this.creditCardExpiryYear = creditCardExpiryYear;
    }

    public Payment() {}

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
