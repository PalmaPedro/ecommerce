package models;

import java.time.LocalDate;

public class CreditCardStrategy implements IPaymentStrategy {

    private final String name;
    private final String cardNumber;
    private final LocalDate expireDate;

    public CreditCardStrategy(String name, String cardNumber, LocalDate expireDate) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.expireDate = expireDate;
    }

    public String getName() {
        return name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Payment successful using your Credit Card");
    }
}

