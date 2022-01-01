package models;

public class HeaderDecorator extends InvoiceDecorator{
    @Override
    public void createInvoice() {
        addHeader();
        baseInvoice.createInvoice();
    }

    public void addHeader() {
        data = """
                \tCYKLER.DK
                \tKøbenhavn, Denmark
                \t2300 København S
                """;
    }
}
