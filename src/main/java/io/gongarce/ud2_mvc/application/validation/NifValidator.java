package io.gongarce.ud2_mvc.application.validation;

import io.gongarce.ud2_mvc.domain.person.Person;

/**
 *
 * @author Gonzalo
 */
public class NifValidator {

    public boolean isValid(Person person) {
        
        String letters = "TRWAGMYFPDXBNJZSQVHLCKE";
        int number;
        int mod;

        String nif = person.getNif();
        if (nif.length() != 9) {
            return false;
        }

        try {
            number = Integer.valueOf(nif.substring(0, 8));
        } catch (NumberFormatException e) {
            return false;
        }

        char last = nif.charAt(8);
        if (!(last >= 'A' && last <= 'Z')){
            return false;
        }
        mod = number%23;

        return last == letters.charAt(mod);

    }
}
