package dev.ms.production.model;

import java.util.Objects;

/**
 * Predstavlja adresu s informacijama o ulici, kućnom broju i gradu.
 */
public class Address {

    public Long id;
    private String street;
    private String houseNumber;
    private String city;

    /**
     * Stvara novi objekt Address s navedenom ulicom, kućnim brojem i gradom.
     *
     * @param street      Naziv ulice.
     * @param houseNumber Kućni broj.
     * @param city        Naziv grada.
     */
    public Address(String street, String houseNumber, String city) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
    }

    /**
     * Stvara novi objekt Address s navedenim identifikatorom, ulicom, kućnim brojem i gradom.
     *
     * @param id          Jedinstveni identifikator adrese.
     * @param street      Naziv ulice.
     * @param houseNumber Kućni broj.
     * @param city        Naziv grada.
     */
    public Address(Long id, String street, String houseNumber, String city) {
        this.id = id;
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) && Objects.equals(houseNumber, address.houseNumber) && Objects.equals(city, address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, houseNumber, city);
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "'" + city + ", " + street + " " + houseNumber + "'";
    }
}
