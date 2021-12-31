package models;

public class PaypalStrategy implements IPaymentStrategy {

    private final String username;
    private final String email;

    public PaypalStrategy(String email, String username) {
        this.email = email;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Payment successful using Paypal");
    }
}

