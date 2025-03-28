package io.gongarce.ud2_mvc.domain.person;

import io.gongarce.ud2_mvc.domain.person.error.NifExistingException;
import io.gongarce.ud2_mvc.domain.person.error.SavePersonException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Gonzalo
 */
public interface PersonRepository {

    Person save(Person person) throws SavePersonException, NifExistingException;

    Optional<Person> get(String nif);

    List<Person> getAll();

    List<Person> getByMail(String mail);
}
