package models;

public class FooterDecorator extends InvoiceDecorator {
    @Override
    public void createInvoice() {
        baseInvoice.createInvoice();
        addFooter();
    }

    public void addFooter() {
        String footer = """
                +=============== Invoice footer ===============+"
                |Copyright @2021 All rights reserved
                """;

        data += footer;
    }
}
