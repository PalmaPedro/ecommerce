package models;

public class MobilePayStrategy implements IPaymentStrategy {

    private final String mobileNumber;

    public MobilePayStrategy(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Payment successful using Mobile Pay");
    }
}

