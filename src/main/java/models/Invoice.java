package models;

import java.time.LocalDate;
import java.util.List;

public class Invoice extends BaseInvoice {
    private final long invoiceNo;
    private final Customer customer;
    private final Item item;
    private List<Item> items;
    private final LocalDate purchaseDate;
    //private final int quantity = 1; //change to totalitemsQTY bought
    private final double invoiceTotal;

    private Invoice(InvoiceBuilder builder) {
        this.invoiceNo = builder.invoiceNo;
        this.customer = builder.customer;
        this.item = builder.item;
        this.items = builder.items;
        this.purchaseDate = builder.purchaseDate;
        this.invoiceTotal = builder.invoiceTotal;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public static class InvoiceBuilder {
        private long invoiceNo;
        private Customer customer;
        private Item item;
        private List<Item> items;
        private LocalDate purchaseDate;
        private double invoiceTotal;

        public InvoiceBuilder() {}

        public Invoice build() { return new Invoice(this); }

        public InvoiceBuilder withInvoiceNo(long invoiceNo) {
            this.invoiceNo = invoiceNo;
            return this;
        }

        public InvoiceBuilder belongsTo(Customer customer) {
            this.customer = customer;
            return this;
        }

        public InvoiceBuilder forItem(Item item) {
            this.item = item;
            return this;
        }

        public InvoiceBuilder itemBought(LocalDate purchaseDate) {
            this.purchaseDate = purchaseDate;
            return this;
        }

        public InvoiceBuilder hasTotal(double invoiceTotal) {
            this.invoiceTotal = invoiceTotal;
            return this;
        }
    }

    @Override
    public String toString() {

        return "Invoice No\tEMAIL\t\tItemName\tItemCode\tPrice\tQuantity\tItemTotal\tPurchaseDate | \n"
                +" "+ invoiceNo + "\t\t" + customer.getEmail() + "\t" + item.getName() + "\t" + item.getItemCode() +"\t" + item.getBasePrice() +"\t" + item.getQuantity() +"\t\t" + item.getBasePrice() + "\t\t"+ getPurchaseDate() + "| \n";
    }

    @Override
    public void createInvoice(){
        data +=  "+================ Invoice body =================+\n"
                + "|Customer Email: " + customer.getEmail() + "\t" + " Date: " + getPurchaseDate() + "|\n"
                + "|Invoice No: " + invoiceNo + "|\n"
                + "|P.No\t\tName\t\t\tItemCode\t\tPrice\t\tQuantity\t\tTotal  | \n"
                + "|" + item.getItemId() + "\t" + item.getName() + "\t\t" + item.getItemCode() + "\t\t\t" + item.getBasePrice() + "\t\t" + item.getQuantity() + "\t\t\t" + "     " + item.getBasePrice() + " | \n"
                + "|\n"
                + "|Total = " + item.getBasePrice() + " DKK |\n"
                + "|Thank you for your purchase!|\n";
    }



}


