package models;

public class FooterDecorator extends InvoiceDecorator {
    @Override
    public void createInvoice() {
        baseInvoice.createInvoice();
        addFooter();
    }

    public void addFooter() {
        String footer = """
                \n\tCopyright @2021 All rights reserved
                """;

        data += footer;
    }
}
