package dev.ms.production.utils;

import dev.ms.production.exception.DuplicateOibException;
import dev.ms.production.model.Phonebook;

import java.util.List;

public class CheckExceptions {

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
