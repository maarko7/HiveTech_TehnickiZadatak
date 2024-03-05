package dev.ms.production.main;

import dev.ms.production.model.Address;
import dev.ms.production.model.Phonebook;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FileUtils {

    Scanner scanner = new Scanner(System.in);

    public static Phonebook createNewUser(Scanner scanner) {
        Long oib = -1L;
        String firstName;
        String lastName;
        String city;
        String streetName;
        String houseNumber;
        Address address;
        Long phoneNumber = -1L;

        Boolean errorInput;
        do {
            errorInput = false;
            try {
                System.out.println("Unesite OIB (11 znamenki): ");
                String input = scanner.nextLine();
                if (input.length() != 11) {
                    throw new IllegalArgumentException("OIB mora imati tocno 11 znamenki. Pokusajte ponovno.");
                }
                oib = Long.parseLong(input);

            } catch (InputMismatchException e) {
                errorInput = true;
                System.out.println("Unijeli ste neispravan format. OIB se mora sastojati samo od znamenki. Pokusajte ponovno.");
            }
        } while (errorInput);

        do {
            errorInput = false;
            System.out.println("Unesite ime: ");
            firstName = scanner.nextLine();
            if (firstName.matches("[a-zA-Z]+")) {
                errorInput = true;
                System.out.println("Neispravan format. Ime moze sadrzavati samo slova. Pokusajte ponovno.");
            }
        } while (errorInput);

        do {
            errorInput = false;
            System.out.println("Unesite prezime: ");
            lastName = scanner.nextLine();
            if (lastName.matches("[a-zA-Z]+")) {
                errorInput = true;
                System.out.println("Neispravan format. Prezime moze sadrzavati samo slova. Pokusajte ponovno.");
            }
        } while (errorInput);

        do {
            errorInput = false;
            System.out.println("Unesite naziv grada: ");
            city = scanner.nextLine();
            if (city.matches("[a-zA-Z]+")) {
                errorInput = true;
                System.out.println("Neispravan format. Naziv grada moze sadrzavati samo slova. Pokusajte ponovno.");
            }
        } while (errorInput);

        do {
            errorInput = false;
            System.out.println("Unesite naziv ulice: ");
            streetName = scanner.nextLine();
            if (streetName.matches("[a-zA-Z]+")) {
                errorInput = true;
                System.out.println("Neispravan format. Naziv grada moze sadrzavati samo slova. Pokusajte ponovno.");
            }
        } while (errorInput);

        System.out.println("Unesite kućni broj: ");
        houseNumber = scanner.nextLine();

        address = new Address(streetName, houseNumber, city);

        do {
            errorInput = false;
            try {
                System.out.println("Unesite broj mobitela u formatu (09xxxxxxxx): ");
                String input = scanner.nextLine();
                if (input.length() < 10 || input.length() > 11) {
                    throw new IllegalArgumentException("Broj mora imati 10 ili 11 znamenki. Pokusajte ponovno.");
                }
                phoneNumber = Long.parseLong(input);

            } catch (InputMismatchException e) {
                errorInput = true;
                System.out.println("Unijeli ste neispravan format. OIB se mora sastojati samo od znamenki. Pokusajte ponovno.");
            }
        } while (errorInput);

        return new Phonebook(oib, firstName, lastName, address, phoneNumber);
    }

}
