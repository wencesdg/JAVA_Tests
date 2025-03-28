package io.gongarce.ud2_mvc.application.mocks;

import io.gongarce.ud2_mvc.domain.person.Person;
import io.gongarce.ud2_mvc.domain.person.PersonRepository;
import io.gongarce.ud2_mvc.domain.person.error.NifExistingException;
import io.gongarce.ud2_mvc.domain.person.error.SavePersonException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Gonzalo
 */
public class PersonRepositoryMock implements PersonRepository {

    @Override
    public List<Person> getAll() {
        return List.of(
                new Person(1l, "123456789A", "Gonzalo", "A Coruña", null, null),
                new Person(2l, "123456789A", "Alejandro", "Madrid", null, null)
        );
    }

    @Override
    public List<Person> getByMail(String mail) {
        return List.of(
                new Person(1l, "123456789A", "Elena", "A Coruña", null, null),
                new Person(2l, "123456789A", "Paula", "Pontevedra", null, null)
        );
    }

    @Override
    public Person save(Person person) throws SavePersonException, NifExistingException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<Person> get(String nif) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}