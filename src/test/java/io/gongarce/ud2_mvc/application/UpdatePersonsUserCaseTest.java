/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package io.gongarce.ud2_mvc.application;

import io.gongarce.ud2_mvc.application.validation.PhoneValidator;
import io.gongarce.ud2_mvc.domain.error.NotFoundException;
import io.gongarce.ud2_mvc.domain.person.Person;
import io.gongarce.ud2_mvc.domain.person.PersonRepository;
import io.gongarce.ud2_mvc.domain.person.error.ModifyNifException;
import io.gongarce.ud2_mvc.domain.person.error.WrongPhoneException;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

/**
 *
 * @author winch
 */
public class UpdatePersonsUserCaseTest {
    
    PersonRepository personRepository;

    private UpdatePersonsUserCase updatePersonsUserCase;
    
    private PhoneValidator phoneValidator;
    
    
    @BeforeEach
    public void UpdatePersonsUserCaseTest() {
        personRepository = Mockito.mock(PersonRepository.class);
        phoneValidator = Mockito.mock(PhoneValidator.class);
        updatePersonsUserCase = new UpdatePersonsUserCase(personRepository, phoneValidator);
    }

    @Test
    public void shouldRaiseAnExceptionWhenPersonNotFound() {
        // with
        Person person = new Person(1l, "123456789A", "Gonzalo", "A Coruña", null, null);
        Mockito.when(personRepository.get(person.getNif())).thenReturn(Optional.empty());
        
        // when 
        Assertions.assertThrows(NotFoundException.class, () -> updatePersonsUserCase.update(person));
    }
    
    @Test
    public void shouldRaiseAnExceptionWhenTryToModifyNif(){
        // with 
        var existingPerson = new Person(1l, "11111111A", "Gonzalo", "A Coruña", null, null);
        var updatedPerson = new Person(1l, "22222222B", "Gonzalo", "A Coruña", null, null);
        Mockito.when(personRepository.get(updatedPerson.getNif())).thenReturn(Optional.of(existingPerson));
        Mockito.when(phoneValidator.isValid(updatedPerson)).thenReturn(true);
        // when 
        Assertions.assertThrows(ModifyNifException.class, () -> updatePersonsUserCase.update(updatedPerson));
        
        
    }
    
    @Test
    public void shouldRaiseWorngPhoneException(){
        // with
        Person person = new Person(1l, "123456789A", "Gonzalo", "A Coruña", null, List.of("159132698","489657321"));
        Person existingPerson = new Person(1L, "12345678A", "Gonzalo", "A Coruña", null, List.of("159132698","489657321"));  // Persona existente
        Mockito.when(personRepository.get(person.getNif())).thenReturn(Optional.of(existingPerson));  // Mock del repositorio para devolver la persona existente
        Mockito.when(phoneValidator.isValid(person)).thenReturn(false);
        
        // when
        Assertions.assertThrows(WrongPhoneException.class, () -> updatePersonsUserCase.update(person));
    }
    
    
    
    
}
