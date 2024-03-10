package dev.ms.production.utils;

import dev.ms.production.exception.DuplicateOibException;
import dev.ms.production.model.Phonebook;

import java.util.List;

/**
 * Utility klasa za provjeru iznimki.
 */
public class CheckExceptions {

    /**
     * Metoda za provjeru jedinstvenosti OIB-a.
     *
     * @param oib           OIB koji treba provjeriti.
     * @param phonebookList Lista korisnika u imeniku.
     * @throws DuplicateOibException Ako OIB već postoji u imeniku.
     */
    public static void checkUniqueId(Long oib, List<Phonebook> phonebookList) throws DuplicateOibException {
        for (Phonebook phonebook : phonebookList) {
            if (phonebook != null) {
                if (phonebook.getOib().equals(oib)) {
                    throw new DuplicateOibException("Korisnik je unio OIB koji vec postoji: " + oib);
                }
            }
        }
    }
}
