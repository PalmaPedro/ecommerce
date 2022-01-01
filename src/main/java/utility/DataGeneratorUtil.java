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
    String cAddr = "Pittsburgh PA, USA";
    Customer c = new Customer(cId, cPassword, cEmail, cName, cAddr, cNumber);

    long pThreeId = 3;
    String pTestName3 = "Kildmeoes Urban   ";
    String pTestCode3 = "KILU01";
    double pTestPrice3 = 5.199;
    int quantityTest3 = 1;
    double item3Total = pTestPrice3;

    Item p3 = new Item(pThreeId, pTestName3, pTestCode3, pTestPrice3, quantityTest3, item3Total);


    public void generateCustomers(Map<String, Customer> customers) {
        customers.put(cEmail, c);
    }

    public void generateItems(Map<Long, Item> items) {

        long pId = 1;
        String pName = "Centurion Ultimate";
        String pCode = "CENT01";
        double pPrice = 5.999;
        int quantity = 1;
        Item p = new Item(pId, pName, pCode, pPrice, quantity, pPrice);
        items.put(pId, p);

        long pTwoId = 2;
        String pTestName = "MBK Genesis       ";
        String pTestCode = "MBK01";
        double pTestPrice = 6.399;
        int quantityTest = 1;
        Item p2 = new Item(pTwoId, pTestName, pTestCode, pTestPrice, quantityTest, pPrice);
        items.put(pTwoId, p2);
        items.put(pThreeId, p3);

        long pId4 = 4;
        //String pName4 = "How to be a Hero By Daniel Birmingham unabridged edition";
        String pName4 = "Scott Metrix 30 EQ ";
        String pCode4 = "SCT01";
        double pPrice4 = 6.799;
        int quantityTest4 = 1;
        Item p4 = new Item(pId4, pName4, pCode4, pPrice4, quantityTest4, pPrice4);
        items.put(pId4, p4);
    }

    public void generateInvoices(Map<Long, BaseInvoice> invoices, Map<String, Item>itemCodes) {
        long invoiceId = 1111;
        LocalDate today = LocalDate.now();
        LocalDate after15days = today.plusDays(16);

        Invoice invoice = new Invoice(invoiceId, c, p3, after15days, p3.getBasePrice());
        invoices.put(invoiceId, invoice);
        itemCodes.put(p3.getItemCode(), p3);

    }
}
