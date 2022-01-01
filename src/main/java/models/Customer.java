package models;

public class Customer implements ICustomer {

    private final String id;
    private final String password;
    private final String email;// Unique constraint
    private final String name; //make first name and last name separate
    private final String address;
    private final String contactNumber;

    private Customer(CustomerBuilder builder) {
        this.id = builder.id;
        this.password = builder.password;
        this.email = builder.email;
        this.name = builder.name;
        this.address = builder.address;
        this.contactNumber = builder.contactNumber;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getCustomerAddress() {
        return address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public static class CustomerBuilder {
        private String id;
        private String password;
        private String name;
        private String email;
        private String address;
        private String contactNumber;

        public CustomerBuilder() {}

        public Customer build() { return new Customer(this); }

        public CustomerBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public CustomerBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public CustomerBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public CustomerBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public CustomerBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public CustomerBuilder withContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
            return this;
        }
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id ='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", address='" + getCustomerAddress() + '\'' +
                ", contact='" + getContactNumber() + '\'' +
                '}';
    }

    public void update(Item item) {
        System.out.println("Dear " + this.name + ", " + item.getName()
                + " Bicycle, is now available at " + item.getCurrentPrice()
                + "; Discount = " + item.getDiscount() + " %");
    }


}
