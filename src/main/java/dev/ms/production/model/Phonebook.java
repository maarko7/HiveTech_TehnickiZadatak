package dev.ms.production.model;

public class Phonebook {

    Long id;

    private Long oib;
    private String firstName;
    private String lastName;
    private Address address;
    private Long phoneNumber;

    public Phonebook() {
    }

    public Phonebook(Long oib, String firstName, String lastName,
                     Address address, Long phoneNumber) {
        this.oib = oib;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOib() {
        return oib;
    }

    public void setOib(Long oib) {
        this.oib = oib;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Imenik{" +
                "oib=" + oib +
                ", ime='" + firstName + '\'' +
                ", prezime='" + lastName + '\'' +
                ", adresa=" + address +
                ", broj=" + phoneNumber +
                '}';
    }
}
