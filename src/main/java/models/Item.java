package models;

import java.util.ArrayList;
import java.util.List;

public class Item implements IItem{
    private final long itemId;
    private final String name;
    private final String itemCode;
    private final double basePrice;
    private double currentPrice;
    private final int quantity;
    private final double itemTotal;
    private final List<Customer> customers = new ArrayList<>();

    private Item(ItemBuilder builder) {
        this.itemId = builder.itemId;
        this.name = builder.name;
        this.itemCode = builder.itemCode;
        this.basePrice = builder.basePrice;
        this.currentPrice = builder.currentPrice;
        this.quantity = builder.quantity;
        this.itemTotal = builder.itemTotal;
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

    public long getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public String getItemCode() {
        return itemCode;
    }

    public static class ItemBuilder {
        private long itemId;
        private String name;
        private String itemCode;
        private double basePrice;
        private double currentPrice;
        private int quantity;
        private double itemTotal;

        public ItemBuilder() {}

        public Item build() { return new Item(this); }

        public ItemBuilder withItemId(long itemId) {
            this.itemId = itemId;
            return this;
        }

        public ItemBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ItemBuilder withItemCode(String itemCode) {
            this.itemCode = itemCode;
            return this;
        }

        public ItemBuilder withBasePrice(double basePrice) {
            this.basePrice = basePrice;
            return this;
        }

        public ItemBuilder withCurrentPrice(double currentPrice) {
            this.currentPrice = currentPrice;
            return this;
        }

        public ItemBuilder withQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public ItemBuilder withItemTotal(double itemTotal) {
            this.itemTotal = itemTotal;
            return this;
        }
    }

    @Override
    public String toString() {
        return  "\t" + getName() + "\t" + getItemCode() + "\t" + getBasePrice();
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
