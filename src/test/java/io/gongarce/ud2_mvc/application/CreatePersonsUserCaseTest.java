package io.gongarce.ud2_mvc.application;

import io.gongarce.ud2_mvc.domain.person.Person;
import io.gongarce.ud2_mvc.domain.person.PersonRepository;
import io.gongarce.ud2_mvc.domain.person.error.WrongNifException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 *
 * @author GonGarce https://github.com/gongarce
 */
public class CreatePersonsUserCaseTest {
    
    PersonRepository personRepository;

    private final CreatePersonsUserCase personUseCase;
    
    public CreatePersonsUserCaseTest() {
        personRepository = Mockito.mock(PersonRepository.class);
        this.personUseCase = new CreatePersonsUserCase(personRepository);
    }

    @Test
    public void shouldRaiseAnExceptionWhenInvalidNif() {
        // with
        Person person = new Person(1l, "123456789A", "Gonzalo", "A Coruña", null, null);

        // when
        Assertions.assertThrows(WrongNifException.class, () -> personUseCase.create(person));
    }
}
