package models;

import java.math.BigDecimal;

public class Item {
    private final long pId;
    private final String name;
    private final String itemCode;
    private final BigDecimal itemPrice;
    private final int quantity;
    private BigDecimal itemTotal;

    public Item(long pId, String name, String itemCode, BigDecimal itemPrice, int quantity, BigDecimal itemTotal) {
        this.pId = pId;
        this.name = name;
        this.itemCode = itemCode;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
        this.itemTotal = itemTotal;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getItemTotal() {
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

        return  "\t" + name + "\t" + itemCode + "\t" + itemPrice;

    }

}
