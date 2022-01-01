package models;

public class HeaderDecorator extends InvoiceDecorator{
    @Override
    public void createInvoice() {
        addHeader();
        baseInvoice.createInvoice();
    }

    public void addHeader() {
        data = """
                +=============== Invoice header ===============+"
                |CYKLER.DK
                |København, Denmark
                |2300 København S
                """;
    }
}
