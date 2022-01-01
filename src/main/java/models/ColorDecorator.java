package models;

import utility.ColorEnum;

public class ColorDecorator extends InvoiceDecorator{
    @Override
    public void createInvoice() {
        addColor();
        baseInvoice.createInvoice();
    }

    public void addColor() {
        System.out.println(ColorEnum.BLUE_BOLD + "\t**COLOR DECORATOR ADDED TO INVOICE**\n");
    }
}
