package io.gongarce.ud2_mvc.presentation;

import com.google.common.collect.Lists;
import io.gongarce.ud2_mvc.application.CreatePersonsUserCase;
import io.gongarce.ud2_mvc.application.GetPersonsUserCase;
import io.gongarce.ud2_mvc.application.UpdatePersonsUserCase;
import io.gongarce.ud2_mvc.domain.error.NotFoundException;
import io.gongarce.ud2_mvc.domain.person.Person;
import io.gongarce.ud2_mvc.domain.person.error.ModifyNifException;
import io.gongarce.ud2_mvc.domain.person.error.NifExistingException;
import io.gongarce.ud2_mvc.domain.person.error.SavePersonException;
import io.gongarce.ud2_mvc.domain.person.error.WrongNifException;
import io.gongarce.ud2_mvc.domain.person.error.WrongPhoneException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gonzalo
 * This class is just to illustrate how to use UseCases on the app main flow
 */
public class Main {

    public static void main(String[] args) {
        GetPersonsUserCase getPersonsUseCase = App.get(GetPersonsUserCase.class);
        CreatePersonsUserCase createPersonsUserCase = App.get(CreatePersonsUserCase.class);

        try {
            createPersonsUserCase.create(new Person(null, "12345678T", "Paula", "Pontevedra", null, Lists.newArrayList("666555444")));
        } catch (SavePersonException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NifExistingException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WrongNifException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WrongPhoneException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            App.get(UpdatePersonsUserCase.class).update(new Person(2L, "11223344A", "Paula", "Pontevedra", null, Lists.newArrayList("666555444")));
        } catch (SavePersonException | WrongNifException | WrongPhoneException | NotFoundException | ModifyNifException | NifExistingException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        getPersonsUseCase.get(Optional.empty()).forEach((p) -> {
            System.out.println(p.getNif() + ": " + p.getName());
            p.getMails().forEach(System.out::println);
            p.getPhones().forEach(System.out::println);
        });
    }
}
