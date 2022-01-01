package models;

public abstract class InvoiceDecorator extends BaseInvoice {
    BaseInvoice baseInvoice;

    public void attachTo(BaseInvoice invoice) {
        this.baseInvoice = invoice;
    }

    @Override
    public void createInvoice() { baseInvoice.createInvoice();
    }
}
