package io.gongarce.ud2_mvc.application;

import io.gongarce.ud2_mvc.application.mocks.PersonRepositoryMock;
import io.gongarce.ud2_mvc.domain.person.Person;
import io.gongarce.ud2_mvc.domain.person.PersonRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 *
 * @author gag
 */
public class GetPersonsUserCaseTest {

    PersonRepository personRepository;

    private final GetPersonsUserCase personUseCase;

    public GetPersonsUserCaseTest() {
        personRepository = Mockito.mock(PersonRepository.class);
        this.personUseCase = new GetPersonsUserCase(personRepository);
    }

    @Test
    public void shouldReturnAllPersonsWhenEmptyOptional() {
        // with
        Optional<String> empty = Optional.empty();
        List<Person> expected = Arrays.asList(
                new Person(1l, "123456789A", "Gonzalo", "A Coruña", null, null),
                new Person(2l, "123456789A", "Alejandro", "Madrid", null, null));
        Mockito.when(personRepository.getAll()).thenReturn(expected);

        // when
        List<Person> actual = personUseCase.get(empty);

        // then
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(expected.get(0), actual.get(0));
    }

    @Test
    public void shouldReturnPersonsWithEmailWhenPresent() {
        // with
        String email = "gonzalo@gmail.com";
        List<Person> expected = Arrays.asList(
                new Person(1l, "123456789A", "Gonzalo", "A Coruña", null, null),
                new Person(2l, "123456789A", "Alejandro", "Madrid", null, null));
        Mockito.when(personRepository.getByMail(email)).thenReturn(expected);

        // when
        List<Person> actual = personUseCase.get(Optional.of(email));

        // then
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(expected.get(0), actual.get(0));
        Mockito.verify(personRepository, Mockito.times(1)).getByMail(email);
        Mockito.verify(personRepository, Mockito.only()).getByMail(email);
    }
}
