package dev.ms.production.utils;

import dev.ms.production.exception.DuplicateOibException;
import dev.ms.production.model.Address;
import dev.ms.production.model.Phonebook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Utility klasa koja pruža različite opcije za upravljanje imenikom.
 */
public class PhonebookUtils {

    private static final Logger logger = LoggerFactory.getLogger(PhonebookUtils.class);

    /**
     * Metoda za prikazivanje opcija programa.
     *
     * @param scanner Scanner objekt za unos korisnika.
     */
    public static void programOptions(Scanner scanner) {
        List<Phonebook> phonebookList = new ArrayList<>();
        Boolean errorInput = true;
        Integer choosenIndex;
        do {
            try {
                System.out.println("Unosom broja ispred teksta odaberite zeljenu akciju: ");
                System.out.println("1. Pregled imenika\n" +
                        "2. Unos novog korisnika\n" +
                        "3. Brisanje korisnika\n" +
                        "4. Kraj programa");
                choosenIndex = scanner.nextInt();
                scanner.nextLine();
                switch (choosenIndex) {
                    case 1 -> searchPhonebookList(scanner, phonebookList);
                    case 2 -> addUserToPhonebookList(scanner, phonebookList);
                    case 3 -> deleteUserFromPhonebookList(scanner, phonebookList);
                    case 4 -> errorInput = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Pogresan odabir. Pokusajte ponovno.");
                logger.error("Korisnik je unio nedozvoljenu vrijednost", e);
                scanner.nextLine();
            }

        } while (errorInput);
    }

    /**
     * Metoda za brisanje korisnika iz imenika.
     *
     * @param scanner     Scanner objekt za unos korisnika.
     * @param phonebookList Lista korisnika u imeniku.
     */
    public static void deleteUserFromPhonebookList(Scanner scanner, List<Phonebook> phonebookList) {
        Boolean errorInput;
        Integer choosenIndex = -1;

        do {
            errorInput = false;
            System.out.println("Unesite broj ispred korisnika kojeg zelite ukloniti.");
            for (int i = 0; i < phonebookList.size(); i++) {
                System.out.println((i + 1) + ". " + phonebookList.get(i));
            }
            try {
                choosenIndex = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                errorInput = true;
                System.out.println("Pogresan odabir. Pokusajte ponovno");
                logger.error("Korisnik je unio nedozvoljenu vrijednost", e);
                scanner.nextLine();
            }
        } while (errorInput || choosenIndex < 1 || choosenIndex > phonebookList.size());
        phonebookList.remove(choosenIndex - 1);
    }

    /**
     * Metoda za pretraživanje imenika prema različitim kriterijima.
     *
     * @param scanner     Scanner objekt za unos korisnika.
     * @param phonebookList Lista korisnika u imeniku.
     */
    public static void searchPhonebookList(Scanner scanner, List<Phonebook> phonebookList) {
        Boolean errorInput;
        Integer choosenIndex = -1;
        do {
            errorInput = false;
            try {
                System.out.println("Unosom broja ispred teksta odaberite zeljenu akciju:");
                System.out.println("1. Ispis svih korisnika\n" +
                        "2. Pretrazivanje po OIB-u\n" +
                        "3. Pretrazivanje po imenu\n" +
                        "4. Pretrazivanje po prezimenu\n" +
                        "5. Pretrazivanje po adresi\n" +
                        "6. Pretrazivanje po broju");
                choosenIndex = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                errorInput = true;
                System.out.println("Pogresan odabir. Pokusajte ponovno.");
                logger.error("Korisnik je unio nedozvoljenu vrijednost", e);
                scanner.nextLine();
            }
        } while (errorInput || choosenIndex < 1 || choosenIndex > 6);

        switch (choosenIndex) {
            case 1 -> searchAllUsersInPhonebookList(phonebookList);
            case 2 -> searchByOib(scanner, phonebookList);
            case 3 -> searchByFirstName(scanner, phonebookList);
            case 4 -> searchByLastName(scanner, phonebookList);
            case 5 -> searchByAddress(scanner, phonebookList);
            case 6 -> searchByPhoneNumber(scanner, phonebookList);
            default -> programOptions(scanner);
        }
    }

    /**
     * Metoda za pretraživanje imenika i ispis svih korisnika.
     *
     * @param phonebookList Lista korisnika u imeniku.
     */
    public static void searchAllUsersInPhonebookList(List<Phonebook> phonebookList) {
        int i = 0;
        phonebookList.stream()
                .sorted(Comparator.comparing(Phonebook::getLastName))
                .forEach(System.out::println);
    }

    /**
     * Metoda za pretraživanje imenika po broju telefona.
     *
     * @param scanner     Scanner objekt za unos korisnika.
     * @param phonebookList Lista korisnika u imeniku.
     */
    public static void searchByPhoneNumber(Scanner scanner, List<Phonebook> phonebookList) {
        String phoneNumber = null;
        Boolean errorInput;
        String input = null;

        do {
            errorInput = false;
            try {
                System.out.println("Unesite broj mobitela u formatu (09xxxxxxxx): ");
                phoneNumber = scanner.nextLine();

            } catch (IllegalArgumentException e) {
                errorInput = true;
                System.out.println("Pogresan unos. Molimo pokusajte ponovno po preporucenom formatu.");
                logger.error("Korisnik je unio nedozvoljenu vrijednost", e);
            }
        } while (errorInput || input.length() < 10 || input.length() > 11);

        for (Phonebook phonebook : phonebookList) {
            if (phonebook.getPhoneNumber().equals(phoneNumber)) {
                System.out.println("Korisnik pronaden:");
                System.out.println(phonebook);
            } else {
                System.out.println("Korisnik s brojem: " + phoneNumber + " nije pronadjen");
            }
        }
    }

    /**
     * Metoda za pretraživanje imenika po adresi.
     *
     * @param scanner     Scanner objekt za unos korisnika.
     * @param phonebookList Lista korisnika u imeniku.
     */
    public static void searchByAddress(Scanner scanner, List<Phonebook> phonebookList) {
        Address address;
        String city;
        String streetName;
        String houseNumber;

        Boolean errorInput;
        do {
            errorInput = false;
            System.out.println("Unesite naziv grada: ");
            city = scanner.nextLine();
            if (!city.matches("[a-zA-Z]+")) {
                errorInput = true;
                System.out.println("Pogresan unos. Naziv grada moze sadrzavati samo slova. Pokusajte ponovno.");
                logger.error("Korisnik je unio nedozvoljenu vrijednost");
            }
        } while (errorInput);

        do {
            errorInput = false;
            System.out.println("Unesite naziv ulice: ");
            streetName = scanner.nextLine();
            if (!streetName.matches("[a-zA-Z]+")) {
                errorInput = true;
                System.out.println("Pogresan unos. Naziv ulice moze sadrzavati samo slova. Pokusajte ponovno.");
                logger.error("Korisnik je unio nedozvoljenu vrijednost");
            }
        } while (errorInput);

        System.out.println("Unesite kućni broj: ");
        houseNumber = scanner.nextLine();

        address = new Address(streetName, houseNumber, city);

        Boolean found = false;
        for (Phonebook phonebook : phonebookList) {
            if (phonebook.getAddress().equals(address)) {
                System.out.println("Korisnik pronaden:");
                System.out.println(phonebook);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Korisnik s adresom: " + address + " nije pronadjen");
        }
    }

    /**
     * Metoda za pretraživanje imenika po prezimenu.
     *
     * @param scanner     Scanner objekt za unos korisnika.
     * @param phonebookList Lista korisnika u imeniku.
     */
    public static void searchByLastName(Scanner scanner, List<Phonebook> phonebookList) {
        String lastName;
        Boolean errorInput;

        do {
            errorInput = false;
            System.out.println("Unesite prezime: ");
            lastName = scanner.nextLine();
            if (!lastName.matches("[a-zA-Z]+")) {
                errorInput = true;
                System.out.println("Pogresan unos. Prezime moze sadrzavati samo slova. Pokusajte ponovno.");
                logger.error("Korisnik je unio nedozvoljenu vrijednost");
            }
        } while (errorInput);

        Boolean found = true;
        for (Phonebook phonebook : phonebookList) {
            if (phonebook.getLastName().equalsIgnoreCase(lastName)) {
                System.out.println("Korisnik pronaden:");
                System.out.println(phonebook);
                found = false;
            }
        }
        if (found) {
            System.out.println("Korisnik s prezimenom: " + lastName + " nije pronadjen");
        }
    }

    /**
     * Metoda za pretraživanje imenika po imenu.
     *
     * @param scanner     Scanner objekt za unos korisnika.
     * @param phonebookList Lista korisnika u imeniku.
     */
    public static void searchByFirstName(Scanner scanner, List<Phonebook> phonebookList) {
        String firstName;
        Boolean errorInput;

        do {
            errorInput = false;
            System.out.println("Unesite ime: ");
            firstName = scanner.nextLine();
            if (!firstName.matches("[a-zA-Z]+")) {
                errorInput = true;
                System.out.println("Pogresan unos. Ime moze sadrzavati samo slova. Pokusajte ponovno.");
                logger.error("Korisnik je unio nedozvoljenu vrijednost");
            }
        } while (errorInput);

        Boolean found = false;
        for (Phonebook phonebook : phonebookList) {
            if (phonebook.getFirstName().equalsIgnoreCase(firstName)) {
                System.out.println("Korisnik pronaden:");
                System.out.println(phonebook);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Korisnik s imenom: " + firstName + " nije pronadjen");
        }

    }

    /**
     * Metoda za pretraživanje imenika po OIB-u.
     *
     * @param scanner     Scanner objekt za unos korisnika.
     * @param phonebookList Lista korisnika u imeniku.
     */
    public static void searchByOib(Scanner scanner, List<Phonebook> phonebookList) {
        Long oib = -1L;
        Boolean errorInput;
        String input = null;
        do {
            errorInput = false;
            try {
                System.out.println("Unesite OIB (11 znamenki): ");
                input = scanner.nextLine();
                oib = Long.parseLong(input);
            } catch (IllegalArgumentException e) {
                errorInput = true;
                System.out.println("Pogresan unos. Molimo unesite 11 znamenki.");
                logger.error("Korisnik je unio nedozvoljenu vrijednost", e);
            }
        } while (errorInput || input.length() != 11);

        Boolean found = false;
        for (Phonebook phonebook : phonebookList) {
            if (phonebook.getOib().equals(oib)) {
                System.out.println("Korisnik pronaden:");
                System.out.println(phonebook);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Korisnik s OIB-om: " + oib + " nije pronadjen");
        }
    }

    /**
     * Metoda za dodavanje novog korisnika u imenik.
     *
     * @param scanner     Scanner objekt za unos korisnika.
     * @param phonebookList Lista korisnika u imeniku.
     */
    public static void addUserToPhonebookList(Scanner scanner, List<Phonebook> phonebookList) {
        phonebookList.add(createNewUser(scanner, phonebookList));
        System.out.println("Korisnik uspjesno dodan u imenik.");
        logger.info("Novi korisnik dodan u imenik.");
    }

    /**
     * Metoda za stvaranje novog korisnika.
     *
     * @param scanner     Scanner objekt za unos korisnika.
     * @param phonebookList Lista korisnika u imeniku.
     * @return Novi korisnik.
     */
    public static Phonebook createNewUser(Scanner scanner, List<Phonebook> phonebookList) {
        Long oib = -1L;
        String firstName;
        String lastName;
        String city;
        String streetName;
        String houseNumber;
        Address address;
        String phoneNumber;

        Boolean errorInput;
        String input = null;
        do {
            errorInput = false;
            try {
                System.out.println("Unesite OIB (11 znamenki): ");
                input = scanner.nextLine();
                oib = Long.parseLong(input);

                CheckExceptions.checkUniqueId(oib, phonebookList);
            } catch (IllegalArgumentException e) {
                errorInput = true;
                System.out.println("Pogresan unos. Molimo unesite 11 znamenki.");
                logger.error("Korisnik je unio nedozvoljenu vrijednost", e);
            } catch (DuplicateOibException e) {
                errorInput = true;
                System.out.println("OIB vec postoji. Pokusajte ponovno.");
                logger.error("Korisnik je unio OIB koji vec postoji", e);
            }
        } while (errorInput || input.length() != 11);

        do {
            errorInput = false;
            System.out.println("Unesite ime: ");
            firstName = scanner.nextLine();
            if (!firstName.matches("[a-zA-Z]+")) {
                errorInput = true;
                System.out.println("Pogresan unos. Ime moze sadrzavati samo slova. Pokusajte ponovno.");
                logger.error("Korisnik je unio nedozvoljenu vrijednost");
            }
        } while (errorInput);

        do {
            errorInput = false;
            System.out.println("Unesite prezime: ");
            lastName = scanner.nextLine();
            if (!lastName.matches("[a-zA-Z]+")) {
                errorInput = true;
                System.out.println("Pogresan unos. Prezime moze sadrzavati samo slova. Pokusajte ponovno.");
                logger.error("Korisnik je unio nedozvoljenu vrijednost");
            }
        } while (errorInput);

        do {
            errorInput = false;
            System.out.println("Unesite naziv grada: ");
            city = scanner.nextLine();
            if (!city.matches("[a-zA-Z]+")) {
                errorInput = true;
                System.out.println("Pogresan unos. Naziv grada moze sadrzavati samo slova. Pokusajte ponovno.");
                logger.error("Korisnik je unio nedozvoljenu vrijednost");
            }
        } while (errorInput);

        do {
            errorInput = false;
            System.out.println("Unesite naziv ulice: ");
            streetName = scanner.nextLine();
            if (!streetName.matches("[a-zA-Z]+")) {
                errorInput = true;
                System.out.println("Pogresan unos. Naziv ulice moze sadrzavati samo slova. Pokusajte ponovno.");
                logger.error("Korisnik je unio nedozvoljenu vrijednost");
            }
        } while (errorInput);

        System.out.println("Unesite kućni broj: ");
        houseNumber = scanner.nextLine();

        address = new Address(streetName, houseNumber, city);

        do {
            errorInput = false;
            System.out.println("Unesite broj mobitela u formatu (09xxxxxxxx): ");
            phoneNumber = scanner.nextLine();
            if (!phoneNumber.matches("[0-9]+") || phoneNumber.length() < 10 || phoneNumber.length() > 11 || !phoneNumber.startsWith("09")) {
                errorInput = true;
                System.out.println("Pogresan unos. Molimo pokusajte ponovno po preporucenom formatu.");
                logger.error("Korisnik je unio nedozvoljenu vrijednost");
            }
        } while (errorInput);

        return new Phonebook(oib, firstName, lastName, address, phoneNumber);
    }

}
