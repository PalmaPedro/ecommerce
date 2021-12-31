package models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Invoice {
    private final long invoiceNo;
    private final Customer customer;
    private final Item item;
    private List<Item> items;
    private final LocalDate purchaseDate;
    //private final int quantity = 1; //change to totalitemsQTY bought
    private final BigDecimal invoiceTotal;

    public Invoice(long invoiceNo, Customer customer, Item item, LocalDate purchaseDate, BigDecimal invoiceTotal) {
        this.invoiceNo = invoiceNo;
        this.customer = customer;
        this.item = item;
        this.purchaseDate = purchaseDate;
        this.invoiceTotal = invoiceTotal;
    }

    public Invoice(long invoiceNo, Customer customer, Item item, BigDecimal invoiceTotal) {
        this.invoiceNo = invoiceNo;
        this.customer = customer;
        this.item = item;
        this.purchaseDate = LocalDate.now();
        this.invoiceTotal = invoiceTotal;
    }


    @Override
    public String toString() {

        return "Invoice No\tEMAIL\t\tItemName\tItemCode\tPrice\tQuantity\tItemTotal\tPurchaseDate | \n"
                +" "+ invoiceNo + "\t\t" + customer.getEmail() + "\t" + item.getName() + "\t" + item.getItemCode() +"\t" + item.getItemPrice() +"\t" + item.getQuantity() +"\t\t" + item.getItemPrice() + "\t\t"+ getPurchaseDate() + "| \n";
    }

    public String showInvoiceDetails(){
        return "+=======Invoice Generated!===========+\n"
                + "|Customer Email: " + customer.getEmail() + "\t"+ " Date: " + purchaseDate + "|\n"
                + "|Invoice No: " + invoiceNo + "|\n"
                + "|P.No\t\tName\t\t\tItemCode\t\tPrice\t\tQuantity\t\tTotal  | \n"
                + "|" + item.getpId() + "\t" + item.getName() + "\t\t" + item.getItemCode() +"\t\t\t" + item.getItemPrice() +"\t\t" + item.getQuantity() +"\t\t\t" + "$"+ item.getItemPrice() + "| \n"
                + "|----------------------------------------------|\n"
                + "|Total = "+ invoiceTotal +" DKK |\n"
                + "|Thank you for your purchase!|\n"
                + "============================================|";
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    //public List<Item> getItems() {
    //    return items;
    //}

}


