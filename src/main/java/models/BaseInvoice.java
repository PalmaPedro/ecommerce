package models;

public abstract class BaseInvoice {
    protected static String data;

    public abstract void createInvoice();

    public void printInvoice() {
        System.out.println(data);
    }
}
