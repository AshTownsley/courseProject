package courseProject;

public class Customer {
    private String customerID;
    private String ssn;
    private String lastName;
    private String firstName;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public Customer(String customerID, String ssn, String lastName, String firstName,
                    String street, String city, String state, String zip, String phone) {
        setCustomerID(customerID);
        setSSN(ssn);
        setLastName(lastName);
        setFirstName(firstName);
        setStreet(street);
        setCity(city);
        setState(state);
        setZip(zip);
        setPhone(phone);
    }

    // Getters
    public String getCustomerID() { return customerID; }
    public String getSSN() { return ssn; }
    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public String getStreet() { return street; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getZip() { return zip; }
    public String getPhone() { return phone; }

    // Setters with validation
    public void setCustomerID(String customerID) {
        if (customerID.length() <= 5 && !customerID.isBlank()) {
            this.customerID = customerID;
        } else {
            throw new IllegalArgumentException("Customer ID must be max 5 characters and not blank.");
        }
    }

    public void setSSN(String ssn) {
        if (ssn.matches("\\d{9}")) {
            this.ssn = ssn;
        } else {
            throw new IllegalArgumentException("SSN must be exactly 9 numeric characters.");
        }
    }

    public void setLastName(String lastName) {
        if (!lastName.isBlank() && lastName.length() <= 20) {
            this.lastName = lastName;
        } else {
            throw new IllegalArgumentException("Last Name must be max 20 characters and not blank.");
        }
    }

    public void setFirstName(String firstName) {
        if (!firstName.isBlank() && firstName.length() <= 15) {
            this.firstName = firstName;
        } else {
            throw new IllegalArgumentException("First Name must be max 15 characters and not blank.");
        }
    }

    public void setStreet(String street) {
        if (!street.isBlank() && street.length() <= 20) {
            this.street = street;
        } else {
            throw new IllegalArgumentException("Street must be max 20 characters and not blank.");
        }
    }

    public void setCity(String city) {
        if (!city.isBlank() && city.length() <= 20) {
            this.city = city;
        } else {
            throw new IllegalArgumentException("City must be max 20 characters and not blank.");
        }
    }

    public void setState(String state) {
        if (state.matches("[A-Za-z]{2}")) {
            this.state = state.toUpperCase();
        } else {
            throw new IllegalArgumentException("State must be exactly 2 alphabetic characters.");
        }
    }

    public void setZip(String zip) {
        if (zip.matches("\\d{5}")) {
            this.zip = zip;
        } else {
            throw new IllegalArgumentException("ZIP must be exactly 5 numeric characters.");
        }
    }

    public void setPhone(String phone) {
        if (phone.matches("\\d{10}")) {
            this.phone = phone;
        } else {
            throw new IllegalArgumentException("Phone number must be exactly 10 numeric characters.");
        }
    }

    @Override
    public String toString() {
        return String.format("Customer ID: %s | Name: %s %s | SSN: %s | Address: %s, %s, %s %s | Phone: %s",
                customerID, firstName, lastName, ssn, street, city, state, zip, phone);
    }
}
