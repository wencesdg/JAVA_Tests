package io.gongarce.ud2_mvc.application;

import io.gongarce.ud2_mvc.application.validation.NifValidator;
import io.gongarce.ud2_mvc.application.validation.PhoneValidator;
import io.gongarce.ud2_mvc.domain.person.Person;
import io.gongarce.ud2_mvc.domain.person.PersonRepository;
import io.gongarce.ud2_mvc.domain.person.error.WrongNifException;
import io.gongarce.ud2_mvc.domain.person.error.WrongPhoneException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 *
 * @author GonGarce https://github.com/gongarce
 */
public class CreatePersonsUserCaseTest {
    
    PersonRepository personRepository;
    
    NifValidator nifValidator;

    private final CreatePersonsUserCase personUseCase;
    
    PhoneValidator phoneValidator;
    
    public CreatePersonsUserCaseTest() {
        personRepository = Mockito.mock(PersonRepository.class);
        nifValidator = Mockito.mock(NifValidator.class);
        phoneValidator = Mockito.mock(PhoneValidator.class);
        this.personUseCase = new CreatePersonsUserCase(personRepository, nifValidator, phoneValidator);
    }

    @Test
    public void shouldRaiseAnExceptionWhenInvalidNif() {
        // with
        Person person = new Person(1l, "123456789A", "Gonzalo", "A Coruña", null, null);
        Mockito.when(nifValidator.isValid(person)).thenReturn(false);

        // when
        Assertions.assertThrows(WrongNifException.class, () -> personUseCase.create(person));
    }
    
    @Test
    public void shouldRaiseAnExceptionWhenInvalidPhone(){
        // with
        Person person = new Person(1l, "123456789A", "Gonzalo", "A Coruña", null, List.of("195845987", "1594863"));
        Mockito.when(phoneValidator.isValid(person)).thenReturn(false);
        
        // when 
        Assertions.assertThrows(WrongPhoneException.class, () -> personUseCase.create(person));
    }
    
    @Test
    public void shouldRaiseAnExceptionWhenExsitingNifFound(){
        
    }
    
    @Test
    public void shouldSavePerson(){
        
    }
}
