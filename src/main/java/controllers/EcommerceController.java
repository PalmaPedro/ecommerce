package controllers;

import models.*;
import utility.ColorEnum;
import utility.DataGeneratorUtil;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class EcommerceController {
    //ItemController itemController = new ItemController();
    //CustomerController customerController = new CustomerController();
    //InvoiceController invoiceController = new InvoiceController();

    DataGeneratorUtil dataGeneratorUtil = new DataGeneratorUtil();
    Map<String, Customer> customers;
    Map<Long, Item> items;
    Map<Long, BaseInvoice> invoices; /// Place to store the invoices a customer creates
    Map<String, Item> itemCodes; /// For item code lookup

    Scanner consoleScan = new Scanner(System.in);

    /**
     * constructor for controller object and to add test data into in-memory
     * collections
     */
    public EcommerceController() {
        customers = new HashMap<>();
        items = new HashMap<>();
        dataGeneratorUtil.generateCustomers(customers);
        dataGeneratorUtil.generateItems(items);
        invoices = new HashMap<>();
        itemCodes = new HashMap<>();
        //dataGeneratorUtil.generateInvoices(invoices, itemCodes);
    }

    /**
     * Main entry point menu for console app for startBrowsing method that returns
     * an int for user choice.
     *
     * @return selected - User menu prompt selection of type int. returns a choice
     *         or exception if invalid input
     */
    private int displayMainMenu() {
        String choiceEntered;
        int selected;
        System.out.println("+================================+");
        System.out.println("| Welcome to CYKLER.DK Web-shop! |");
        System.out.println("|1. Register |");
        System.out.println("|2. Login |");
        System.out.println("|3. Exit app. |");
        System.out.println("+================================+");
        while (true) {
            System.out.println("\nEnter Choice (1, 2, 3) : ");
            choiceEntered = consoleScan.nextLine();
            try {
                selected = Integer.parseInt(choiceEntered);
                break;
            } catch (Exception exception) {
                System.out.println("Enter a valid choice (1, 2, 3). Try Again!");
            }
        }
        System.out.println();
        return selected;
    }

    /**
     * Display a submenu for customer after a successful login that returns an int
     *
     * @return selected - User menu prompt selection of type int. returns a choice
     *         or exception if invalid input
     */
    private int displayCustMenu() {
        String inputToParse;
        int selected;

        System.out.println("+========== Customer Menu ===============+");
        System.out.println("|1. Buy an Item                          |");
        System.out.println("|2. Subscribe for price drop alerts      |");
        System.out.println("|3. Log out                              |"); // return user to main menu
        System.out.println("+========================================+");
        while (true) {
            System.out.println("Enter a number (1,2,3) from Customer menu : ");
            inputToParse = consoleScan.nextLine();
            try {
                selected = Integer.parseInt(inputToParse);
                break;
            } catch (Exception e) {
                System.out.println("Something happened with the try!");
                System.out.println("Please enter a valid choice. Try again!");
            }
        }
        return selected;
    }

    /**
     * Display a submenu for payment options, after selecting an item
     *
     * @return selected - User menu prompt selection of type int. returns a choice
     *          or exception if invalid input
     */
    private int displayPaymentOptMenu() {
        String inputToParse;
        int selected;

        System.out.println("+========== Payment Options Menu ===============+");
        System.out.println("|1. Credit Card                                 |");
        System.out.println("|2. PayPal                                      |");
        System.out.println("|3. MobilePay                                   |"); // return user to main menu
        System.out.println("+================================================");
        while (true) {
            System.out.println("Enter a number (1,2,3) from Payment Options menu : ");
            inputToParse = consoleScan.nextLine();
            try {
                selected = Integer.parseInt(inputToParse);
                break;
            } catch (Exception e) {
                System.out.println("Something happened with the try!");
                System.out.println("Please enter a valid choice. Try again!");
            }
        }
        return selected;
    }

    // Methods
     private void login() {
         Customer customer;
         String cEmail;
         String cPassword;

         System.out.println("Enter Email and Password:");
         System.out.println("Email : ");
         cEmail = consoleScan.nextLine();
         System.out.println("Password : ");
         cPassword = consoleScan.nextLine();
         System.out.println();
         if (customers.containsKey(cEmail)) {
             customer = customers.get(cEmail);
             System.out.println("\n+======Login Successful!=========+");
             System.out.println("Welcome " + customer.getName());
             if (customer.getPassword().equals(cPassword)) {
                 while (true) {
                     showItems();
                     int input = displayCustMenu();
                     switch (input) {
                         case 1 ->
                                 buyItem(customer);
                         case 2 ->
                                 subscribe(customer);
                         case 3 -> //continue mainloop;
                                 System.out.println("Signed out successfully");
                         default -> System.out.println("Enter a valid choice");
                     }
                 }
             } else {
                 System.out.println("Invalid Credentials..Try again");
             }
         } else {
             System.out.println("Invalid Credentials..Try again");
         }
     }

    /***
     * method for creating a customer account
     */
    private void register() {
        Customer customer;
        String email;
        String password;
        String confirmPassword;
        String name;

        System.out.println("Enter your details for a new customer account");

        System.out.println("Name: ");
        name = consoleScan.nextLine();

        System.out.println("Email: ");
        email = consoleScan.nextLine();

        while (customers.containsKey(email)) {
            System.out.println("Email already exists, Try again..");
            email = consoleScan.nextLine();
        }

        System.out.println("Password: ");
        password = consoleScan.nextLine();

        System.out.println("Confirm password: ");
        confirmPassword = consoleScan.nextLine();

        // password check for matching strings.
        try {
            if (password.equals(confirmPassword)) {
                // implement Builder design pattern
                customer = new Customer.CustomerBuilder()
                        .withName(name)
                        .withPassword(password)
                        .withEmail(email)
                        .build();

                customers.put(email, customer);
                System.out.println("Registration Successful");
            } else {
                System.out.println("Password does not match. try again");
            }
        } catch (Exception e) {
            System.out.println("something happened, try again");
        }
    }

    /**
     * Method to display all items for sale from datasource or test data
     *
     */
    private void showItems() {
        Set<Map.Entry<Long, Item>> set = items.entrySet();
        Iterator<Map.Entry<Long, Item>> iterator = set.iterator();
        System.out.println("\n+=============Items in Stock===================+");
        System.out.print("|ProductId \t Name \t\t Item Code \t Price |\t \n");
        if (items.isEmpty()) {
            System.out.println("No purchases have been made on this account ");
        }
        while (iterator.hasNext()) {
            Map.Entry<Long, Item> mentry = iterator.next();
            System.out.println("|" + mentry.getKey() + ". " + mentry.getValue() + "|");
        }
        System.out.println("+=================================================+");
    }

    private void showInvoices() {
        Set<Map.Entry<Long, BaseInvoice>> set = invoices.entrySet();
        Iterator<Map.Entry<Long, BaseInvoice>> iterator = set.iterator();
        System.out.println("\n+================================+");
        while (iterator.hasNext()) {
            Map.Entry<Long, BaseInvoice> mentry = iterator.next();
            System.out.println("|" + mentry.getValue() + "|");
        }
        System.out.println("+================================+");
    }

    /**
     * Method for choosing an item to buy by its key and making it into an invoice
     * by looking for its key value in hashmap.
     */
    private void buyItem(Customer c) {
        // Show list of items
        String choice;
        Long selected;
        Item item; // add values of item to Invoice

        showItems();
        // select an item to buy and search for it by its key in the hashmap
        System.out.println("Enter the product Id of the item you want to purchase");
        // choice
        choice = consoleScan.nextLine();
        selected = Long.parseLong(choice);

        // Add item to shopping cart
        // extract method from this
        ShoppingCart cart = new ShoppingCart();
        if (items.containsKey(selected)) {
            item = items.get(selected);
            cart.addItem(item);
        }

        // Select payment options
        int option = displayPaymentOptMenu();

        // Make payment
        pay(option, cart);

        // Generate invoice
        // extract method
        try {
            if (items.containsKey(selected)) {
                item = items.get(selected);

                generateInvoice(item, c);

                System.out.println("\nPress Enter to continue");

            }
        } catch (Exception e) {
            System.out.println("Please enter the product number from the catalog.");
        }
    }

    /**
     * Method for subscribing a customer to a "price drop" alert
     */
    private void subscribe(Customer customer) {
        // Show list of items
        String choice;
        long selected;

        showItems();
        // select an item to subscribe and search for it by its key in the hashmap
        System.out.println("Enter the product Id of the item you want to subscribe");
        // choice
        choice = consoleScan.nextLine();
        selected = Long.parseLong(choice);

        if (items.containsKey(selected)) {
            // attach or subscriber customer
            items.get(selected).subscribe(customer);

            // show that customer will be notified if price is lower than basePrice
            // this is temporary. add an option to input price change and fetch subscribed customers list to notify all
            items.get(selected).price(5.999);
        }

    }

    /**
     * Method for unsubscribe a customer
     */
    private void unsubscribe(Customer customer) {

    }

    /**
     * Method to generate invoice
     */
    private void generateInvoice(Item item, Customer c) {
        long min = 1L;
        long max = 100L;
        long invoiceNo = min + (long) (Math.random() * (max - min));

        BaseInvoice invoice = new Invoice.InvoiceBuilder()
                .withInvoiceNo(invoiceNo)
                .belongsTo(c)
                .forItem(item)
                .hasTotal(item.getItemTotal())
                .build();

        invoices.put(invoiceNo, invoice);
        itemCodes.put(item.getItemCode(), item);

        // Add color, header and footer to invoice
        InvoiceDecorator colorDecorator = new ColorDecorator();
        InvoiceDecorator headerDecorator = new HeaderDecorator();
        InvoiceDecorator footerDecorator = new FooterDecorator();

        colorDecorator.attachTo(invoice);
        footerDecorator.attachTo(colorDecorator);
        headerDecorator.attachTo(footerDecorator);
        headerDecorator.createInvoice();

        invoice.printInvoice();

        // reset color
        System.out.println(ColorEnum.RESET);

    }

    /**
     * Method to handle payment for an item
     */
    private void pay(int option, ShoppingCart cart) {
        switch (option) {
            case 1 -> creditCardStrategy(cart);
            case 2 -> paypalStrategy(cart);
            case 3 -> mobilePayStrategy(cart);
        }
    }

    /**
     * Method to handle Credit Card strategy
     */
    private void creditCardStrategy (ShoppingCart cart) {
        // pause for 3 seconds to emulate payment being processed
        System.out.println("+======= Payment by Credit Card selected =======+");
        System.out.println("... Payment is being processed");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        cart.pay(new CreditCardStrategy("pedro palma", "111-111-111", LocalDate.of(2023, 12, 12)));
    }

    /**
     * Method to handle Paypal payment strategy
     */
    private void paypalStrategy(ShoppingCart cart) {
        // pause for 3 seconds to emulate payment being processed
        System.out.println("+======= Payment by Paypal selected =======+");
        System.out.println("... Payment is being processed");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        cart.pay(new PaypalStrategy("pedro.palma@gmail.com", "pmcpalma"));
    }

    /**
     * Method to handle MobilePay payment strategy
     */

    private void mobilePayStrategy (ShoppingCart cart) {
        // pause for 3 seconds to emulate payment being processed
        System.out.println("+======= Payment by MobilePay selected =======+");
        System.out.println("... Payment is being processed");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        cart.pay(new MobilePayStrategy("555-555-555"));
    }

    /**
     * Method for checking a purchase by invoice and purchase date and returning an
     * item within a 15 day return policy
     */
    /*
    private void ReplaceItem(Customer c) {
        String input;
        String choice;
        Item itemToReplace;
        Long invoiceNo;
        BaseInvoice enteredInvoice;
        System.out.println("Welcome " + c.getEmail() + "!! Your invoice details are:");
        showInvoices();

        try {
            System.out.println("Enter the invoice number.");
            input = consoleScan.nextLine();
            invoiceNo = Long.parseLong(input);

            if (invoices.containsKey(invoiceNo)) {
                System.out.println("Invoice found");
                enteredInvoice = invoices.get(invoiceNo);

                System.out.println("Enter the itemcode of the item to replace.");
                input = consoleScan.nextLine();

                if (itemCodes.containsKey(input)) {

                    itemToReplace = itemCodes.get(input);
                    long validDate = ChronoUnit.DAYS.between(enteredInvoice.getPurchaseDate(), LocalDate.now());

                    if (validDate >= -15) {
                        System.out.println(
                                "Yes, you can return your purchase. Would you like to proceed (enter y for yes or n for no.)");
                        choice = consoleScan.nextLine();
                        if (choice.equals("y")) {
                            System.out.println("Return successful,Your replaced item is" + itemToReplace.toString());
                            System.out.println("Press Enter to continue");
                        } else if (choice.equals("n")) {
                            System.out.println("Returning back to customer menu");
                            displayCustMenu();
                        } else {
                            System.out.println("Not valid input");
                        }
                    } else {
                        System.out.println(
                                "Your purchase is past the 15 day period. You are not able to return or replace your item(s)");
                        System.out.println("Press Enter to continue");
                        //System.in.read();
                    }

                } else {
                    System.out.println("You dont have this item in your invoice");
                }
            }
        } catch (Exception e) {
            System.out.println("Not a valid choice");

        }
    }*/

    /**
     * Method for starting up ecommerce menu for Customer registration Customer
     * Login Buying an item Replacing an item if broken Exit the program
     */
    public void startBrowsing() {
        try {
            int input; // local function scope
            while (true) {
                input = displayMainMenu();
                switch (input) {
                    case 1 -> register();
                    case 2 -> login();
                    case 3 -> {
                        System.out.println("Thank you for using the CYKLER.DK WebshopCLI");
                        System.exit(1);
                    }
                    default -> System.out.println("Enter a Valid Option!");
                }
                System.out.println();
            }
        } catch (Exception exception) {
            System.out.println("Enter a valid choice. Try Again!");
            exception.printStackTrace();
        }
    }
}
