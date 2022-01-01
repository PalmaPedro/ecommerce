package models;

import java.util.ArrayList;
import java.util.List;

public class Item implements IItem{
    private final long pId;
    private final String name;
    private final String itemCode;
    private final double basePrice;
    private double currentPrice;
    private final int quantity;
    private final double itemTotal;
    private final List<Customer> customers = new ArrayList<>();

    public Item(long pId, String name, String itemCode, double basePrice, int quantity, double itemTotal) {
        this.pId = pId;
        this.name = name;
        this.itemCode = itemCode;
        this.basePrice = basePrice;
        this.currentPrice = basePrice;
        this.quantity = quantity;
        this.itemTotal = itemTotal;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public double getCurrentPrice() { return currentPrice; }

    public void setCurrentPrice(double currentPrice) { this.currentPrice = currentPrice; }

    public double getDiscount () { return (getBasePrice() - getCurrentPrice()) * 100 / getBasePrice(); }

    public void price(double value) {
        setCurrentPrice(value);
        // If price of Item is less thant the base price, notify subscribers
        if (getCurrentPrice() < getBasePrice()) {
            System.out.println("** Price drop alert! **");
            notify_();
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public double getItemTotal() {
        return itemTotal;
    }

    public long getpId() {
        return pId;
    }

    public String getName() {
        return name;
    }

    public String getItemCode() {
        return itemCode;
    }

    @Override
    public String toString() {
        return  "\t" + name + "\t" + itemCode + "\t" + basePrice;
    }

    @Override
    public void subscribe(Customer customer) {
        System.out.println("Customer subscribed...");
        customers.add(customer);
    }

    @Override
    public void unsubscribe(Customer customer) {
        System.out.println("Customer unsubscribed...");
        customers.remove(customer);
    }

    @Override
    public void notify_() {
        for (Customer observer : customers) {
            System.out.println("Customer notified...");
            observer.update(this);
        }
    }
}
