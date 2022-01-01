package utility;

import models.BaseInvoice;
import models.Customer;
import models.Invoice;
import models.Item;

import java.time.LocalDate;
import java.util.Map;

public class DataGeneratorUtil {
    String cId = "C001";
    String cPassword = "password!12";
    String cEmail = "cj@mail.com";
    String cName = "Chris F Jabbour";
    String cNumber = "7244211234";
    String cAddress = "Pittsburgh PA, USA";

    Customer customer = new Customer.CustomerBuilder()
            .withId(cId)
            .withPassword(cPassword)
            .withEmail(cEmail)
            .withName(cName)
            .withAddress(cAddress)
            .withContactNumber(cNumber)
            .build();

    long pThreeId = 3;
    String pTestName3 = "Kildmeoes Urban   ";
    String pTestCode3 = "KILU01";
    double pTestPrice3 = 5.199;
    int quantityTest3 = 1;
    double item3Total = pTestPrice3;

    Item p3 = new Item.ItemBuilder()
            .withItemId(pThreeId)
            .withName(pTestName3)
            .withItemCode(pTestCode3)
            .withBasePrice(pTestPrice3)
            .withQuantity(quantityTest3)
            .withItemTotal(item3Total)
            .build();

    public void generateCustomers(Map<String, Customer> customers) {
        customers.put(cEmail, customer);
    }

    public void generateItems(Map<Long, Item> items) {

        long pId = 1;
        String pName = "Centurion Ultimate";
        String pCode = "CENT01";
        double pPrice = 5.999;
        int quantity = 1;

        Item p = new Item.ItemBuilder()
                .withItemId(pId)
                .withName(pName)
                .withItemCode(pCode)
                .withBasePrice(pPrice)
                .withQuantity(quantity)
                .withBasePrice(pPrice)
                .build();
        items.put(pId, p);

        long pTwoId = 2;
        String pTestName = "MBK Genesis       ";
        String pTestCode = "MBK01";
        double pTestPrice = 6.399;
        int quantityTest = 1;

        Item p2 = new Item.ItemBuilder()
                .withItemId(pTwoId)
                .withName(pTestName)
                .withItemCode(pTestCode)
                .withBasePrice(pTestPrice)
                .withQuantity(quantityTest)
                .withBasePrice(pPrice)
                .build();

        items.put(pTwoId, p2);
        items.put(pThreeId, p3);

        long pId4 = 4;
        //String pName4 = "How to be a Hero By Daniel Birmingham unabridged edition";
        String pName4 = "Scott Metrix 30 EQ ";
        String pCode4 = "SCT01";
        double pPrice4 = 6.799;
        int quantityTest4 = 1;

        //Item p4 = new Item(pId4, pName4, pCode4, pPrice4, quantityTest4, pPrice4);
        Item p4 = new Item.ItemBuilder()
                .withItemId(pId4)
                .withName(pName4)
                .withItemCode(pCode4)
                .withBasePrice(pPrice4)
                .withQuantity(quantityTest4)
                .withBasePrice(pPrice4)
                .build();

        items.put(pId4, p4);
    }

    public void generateInvoices(Map<Long, BaseInvoice> invoices, Map<String, Item>itemCodes) {
        long invoiceId = 1111;
        LocalDate today = LocalDate.now();
        LocalDate after15days = today.plusDays(16);

        //Invoice invoice = new Invoice(invoiceId, customer, p3, after15days, p3.getBasePrice());
        Invoice invoice = new Invoice.InvoiceBuilder()
                .withInvoiceNo(invoiceId)
                .belongsTo(customer)
                .forItem(p3)
                .itemBought(after15days)
                .build();
        invoices.put(invoiceId, invoice);
        itemCodes.put(p3.getItemCode(), p3);
    }
}
