package dev.ms.production.model;


/**
 * Predstavlja unos u telefonskom imeniku s informacijama poput OIB-a, imena, prezimena, adrese i broja telefona.
 */
public class Phonebook {

    Long id;

    private Long oib;
    private String firstName;
    private String lastName;
    private Address address;
    private String phoneNumber;

    /**
     * Konstruktor za stvaranje unosa u imeniku s informacijama.
     *
     * @param oib          OIB osobe.
     * @param firstName    Ime osobe.
     * @param lastName     Prezime osobe.
     * @param address      Adresa osobe.
     * @param phoneNumber Broj telefona osobe.
     */
    public Phonebook(Long oib, String firstName, String lastName,
                     Address address, String phoneNumber) {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
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
