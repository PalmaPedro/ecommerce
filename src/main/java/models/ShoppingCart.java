package models;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<Item> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public double totalCost() {
        double total = 0.0;
        for (Item itemPrice : items) {
            total += itemPrice.getItemPrice().doubleValue();
        }
        return total;
    }

    public void pay(IPaymentStrategy method) {
        double amount = totalCost();
        method.pay(amount);
    }
}
