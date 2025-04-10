package io.gongarce.ud2_mvc.infra.jdbc;

import io.gongarce.ud2_mvc.domain.person.Person;
import io.gongarce.ud2_mvc.domain.person.error.NifExistingException;
import io.gongarce.ud2_mvc.domain.person.error.SavePersonException;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author gag
 */
public class JdbcPersonRepositoryIT {

    private final JdbcPersonRepository repository;

    public JdbcPersonRepositoryIT() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("io.gongarce.h2.test");
        repository = new JdbcPersonRepository(entityManagerFactory.createEntityManager());
    }

    @Test
    public void shouldGetAllUsers() {
        // when
        List<Person> persons = repository.getAll();

        // then
        Assertions.assertEquals(4, persons.size());
        Assertions.assertEquals("11223344A", persons.get(0).getNif());
    }

    @Test
    public void shouldReturnEmptyWhenNotFoundByNif() {
        // with 
        String nif = "NONIF";

        // when
        Optional<Person> person = repository.get(nif);

        // then
        Assertions.assertTrue(person.isEmpty());
    }

    @Test
    public void shouldReturnPersonWhenFoundByNif() {
        // with 
        String nif = "11223344B";

        // when
        Optional<Person> person = repository.get(nif);

        // then
        Assertions.assertTrue(person.isPresent());
        Assertions.assertEquals("Paula", person.get().getName());
    }

    @Test
    public void shouldReturnEmptyListWhenMailNotFound() {
        // with 
        String email = "NOEMAIL";

        // when
        List<Person> persons = repository.getByMail(email);

        // then
        Assertions.assertTrue(persons.isEmpty());
    }

    @Test
    public void shouldReturnListWhithSingleResultByEmail() {
        // with 
        String email = "manuel@gmail.com";

        // when
        List<Person> persons = repository.getByMail(email);

        // then
        Assertions.assertEquals(1, persons.size());
        Assertions.assertEquals("Manuel", persons.get(0).getName());
    }

    @Test
    public void shouldReturnListWhithMultipleResulstByEmailStarts() {
        // with 
        String email = "job.";

        // when
        List<Person> persons = repository.getByMail(email);

        // then
        Assertions.assertEquals(2, persons.size());
    }

    @Test
    public void shouldReturnListWhithMultipleResulstByEmailMiddle() {
        // with 
        String email = "@gmail";

        // when
        List<Person> persons = repository.getByMail(email);

        // then
        Assertions.assertEquals(3, persons.size());
    }

    @Test
    public void shouldRaiseAnExceptionWhenExistingNif() {
        // with
        Person person = new Person(null, "11223344A", "Nuevo user", "Test", null, null);

        // when
        Assertions.assertThrows(NifExistingException.class, () -> repository.save(person));
    }

    @Test
    public void shouldSaveNewPerson() throws SavePersonException, NifExistingException {
        // with
        Person person = new Person(null, "OTRONIF", "Nuevo user", "Test", null, null);

        // when
        Person actual = repository.save(person);
        
        // then
        Optional<Person> expected = repository.get("OTRONIF");
        Assertions.assertEquals(expected.get(), actual);
    }

    @Test
    public void shouldUpdateExistingPerson() throws SavePersonException, NifExistingException {
        // with
        String newPlace = "A Coruña";
        Person updatedPerson = repository.get("11223344A").get().withPlace(newPlace);

        // when
        Person actual = repository.save(updatedPerson);
        
        // then
        Optional<Person> foundPerson = repository.get("11223344A");
        Assertions.assertEquals(newPlace, actual.getPlace());
        Assertions.assertEquals(newPlace, foundPerson.get().getPlace());
    }

}
