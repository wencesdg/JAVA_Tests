package io.gongarce.ud2_mvc.application.validation;

import io.gongarce.ud2_mvc.domain.person.Person;

/**
 *
 * @author Gonzalo
 */
public class NifValidator {

    public static boolean isValid(Person person) {
        String nif = person.getNif();
        if (nif.length() != 9) {
            return false;
        }

        try {
            Integer.valueOf(nif.substring(0, 8));
        } catch (NumberFormatException e) {
            return false;
        }

        char last = nif.charAt(8);
        return last >= 'A' && last <= 'Z';
    }
}
