/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package io.gongarce.ud2_mvc.application.validation;

import io.gongarce.ud2_mvc.domain.person.Person;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author winch
 */
public class NifValidatorTest {
    
    private final NifValidator nifValidator = new NifValidator();
    
   
    @Test
    public void shouldHave9Characters() {
        Person person = new Person(1L, "12345678A", "Juan", "Madrid", null, null);
        assertTrue(nifValidator.isValid(person));
    }
    
    @Test
    public void lastCharMustBeACapitalLetter() {
        Person person = new Person(1L, "12345678A", "Juan", "Madrid", null, null);
        assertTrue(nifValidator.isValid(person));
    }
    
    @Test
    public void first8CharsMustBeNumbers() {
        Person person = new Person(1L, "12345678A", "Juan", "Madrid", null, null);
        assertTrue(nifValidator.isValid(person));
    }
    
    @Test
    public void letterMustBeCorrectBasedOnMod() {
        Person person = new Person(1L, "12345678Z", "Juan", "Madrid", null, null);
        assertTrue(nifValidator.isValid(person));
    }
    
    @Test
    public void shouldReturnFalseHave9Characters() {
        Person person = new Person(1L, "123456A", "Juan", "Madrid", null, null);
        assertFalse(nifValidator.isValid(person));
    }
    
    @Test
    public void shouldReturnFalselastCharMustBeACapitalLetter() {
        Person person = new Person(1L, "12345678a", "Juan", "Madrid", null, null);
        assertFalse(nifValidator.isValid(person));
    }
    
    @Test
    public void shouldReturnFalsefirst8CharsMustBeNumbers() {
        Person person = new Person(1L, "1234x678A", "Juan", "Madrid", null, null);
        assertFalse(nifValidator.isValid(person));
    }
    
    @Test
    public void shouldReturnFalseletterMustBeCorrectBasedOnMod() {
        Person person = new Person(1L, "12345678B", "Juan", "Madrid", null, null);
        assertFalse(nifValidator.isValid(person));
    }
    
}
