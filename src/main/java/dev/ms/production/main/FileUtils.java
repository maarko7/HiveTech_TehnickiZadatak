package dev.ms.production.main;

import dev.ms.production.model.Address;
import dev.ms.production.model.Phonebook;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class FileUtils {

    public static void programOptions(Scanner scanner) {
        List<Phonebook> phonebookList = new ArrayList<>();
        Boolean errorInput;
        Integer choosenIndex = -1;
        do {
            try {
                errorInput = false;
                System.out.println("Unosom broja isred teksta odaberite zeljenu akciju: ");
                System.out.println("1. Pregled imenika\n" +
                        "2. Unos novog korisnika\n" +
                        "3. Kraj programa");
                choosenIndex = scanner.nextInt();
                scanner.nextLine();
                if (choosenIndex < 1 || choosenIndex > 3) {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                errorInput = true;
                System.out.println("Pogresan odabir. Pokusajte ponovno.");
            }
            switch (choosenIndex) {
                case 1 -> searchPhonebookList(scanner, phonebookList);
                case 2 -> addUserToPhonebookList(scanner, phonebookList);
                case 3 -> errorInput = false;
            }

        } while (errorInput);
    }

    public static void searchPhonebookList(Scanner scanner, List<Phonebook> phonebookList) {
        Boolean errorInput;
        Integer choosenIndex = -1;
        do {
            errorInput = false;
            try {
                System.out.println("Unosom broja ispred teksta odaberite zeljenu akciju:");
                System.out.println("1. Pretrazivanje po OIB-u\n" +
                        "2. Pretrazivanje po imenu\n" +
                        "3. Pretrazivanje po prezimenu\n" +
                        "4. Pretrazivanje po adresi\n" +
                        "5. Pretrazivanje po broju");
                choosenIndex = scanner.nextInt();
                scanner.nextLine();
                if (choosenIndex < 1 || choosenIndex > 5) {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                errorInput = true;
                System.out.println("Pogresan odabir. Pokusajte ponovno.");
            }
        } while (errorInput);

        switch (choosenIndex) {
            case 1 -> searchByOib(scanner, phonebookList);
            default -> programOptions(scanner);
        }

    }

    public static void searchByOib(Scanner scanner, List<Phonebook> phonebookList) {
        Long oib = -1L;
        Boolean errorInput;
        do {
            errorInput = false;
            try {
                System.out.println("Unesite OIB (11 znamenki): ");
                String input = scanner.nextLine();
                if (input.length() != 11) {
                    throw new IllegalArgumentException();
                }
                oib = Long.parseLong(input);
            } catch (IllegalArgumentException e) {
                errorInput = true;
                System.out.println("Pogresan unos. Molimo unesite 11 znamenki.");
            }
        } while (errorInput);

        for (Phonebook phonebook : phonebookList) {
            if (phonebook.getOib().equals(oib)) {
                System.out.println("Korisnik pronaden:");
                System.out.println(phonebook);
            } else {
                System.out.println("Korisnik s OIB-om: " + oib + " nije pronadjen");
            }
        }
    }

    public static void addUserToPhonebookList(Scanner scanner, List<Phonebook> phonebookList) {
        phonebookList.add(createNewUser(scanner));
    }

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
                    throw new IllegalArgumentException();
                }
                oib = Long.parseLong(input);
            } catch (IllegalArgumentException e) {
                errorInput = true;
                System.out.println("Pogresan unos. Molimo unesite 11 znamenki.");
            }
        } while (errorInput);

        do {
            errorInput = false;
            System.out.println("Unesite ime: ");
            firstName = scanner.nextLine();
            if (!firstName.matches("[a-zA-Z]+")) {
                errorInput = true;
                System.out.println("Pogresan unos. Ime moze sadrzavati samo slova. Pokusajte ponovno.");
            }
        } while (errorInput);

        do {
            errorInput = false;
            System.out.println("Unesite prezime: ");
            lastName = scanner.nextLine();
            if (!lastName.matches("[a-zA-Z]+")) {
                errorInput = true;
                System.out.println("Pogresan unos. Prezime moze sadrzavati samo slova. Pokusajte ponovno.");
            }
        } while (errorInput);

        do {
            errorInput = false;
            System.out.println("Unesite naziv grada: ");
            city = scanner.nextLine();
            if (!city.matches("[a-zA-Z]+")) {
                errorInput = true;
                System.out.println("Pogresan unos. Naziv grada moze sadrzavati samo slova. Pokusajte ponovno.");
            }
        } while (errorInput);

        do {
            errorInput = false;
            System.out.println("Unesite naziv ulice: ");
            streetName = scanner.nextLine();
            if (!streetName.matches("[a-zA-Z]+")) {
                errorInput = true;
                System.out.println("Pogresan unos. Naziv ulice moze sadrzavati samo slova. Pokusajte ponovno.");
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
                    throw new IllegalArgumentException();
                }
                phoneNumber = Long.parseLong(input);

            } catch (IllegalArgumentException e) {
                errorInput = true;
                System.out.println("Pogresan unos. Molimo pokusajte ponovno po preporucenom formatu.");
            }
        } while (errorInput);

        return new Phonebook(oib, firstName, lastName, address, phoneNumber);
    }

}
