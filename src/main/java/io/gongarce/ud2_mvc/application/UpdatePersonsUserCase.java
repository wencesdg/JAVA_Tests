package io.gongarce.ud2_mvc.application;

import com.google.inject.Inject;
import io.gongarce.ud2_mvc.application.validation.PhoneValidator;
import io.gongarce.ud2_mvc.application.validation.Validator;
import io.gongarce.ud2_mvc.domain.error.NotFoundException;
import io.gongarce.ud2_mvc.domain.person.Person;
import io.gongarce.ud2_mvc.domain.person.PersonRepository;
import io.gongarce.ud2_mvc.domain.person.error.ModifyNifException;
import io.gongarce.ud2_mvc.domain.person.error.NifExistingException;
import io.gongarce.ud2_mvc.domain.person.error.SavePersonException;
import io.gongarce.ud2_mvc.domain.person.error.WrongNifException;
import io.gongarce.ud2_mvc.domain.person.error.WrongPhoneException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Gonzalo
 */
@RequiredArgsConstructor(onConstructor = @__({
    @Inject}))
public class UpdatePersonsUserCase implements UseCase {

    private final PersonRepository personRepository;

    public Person update(@NonNull Person person) 
            throws SavePersonException, WrongNifException, WrongPhoneException, NotFoundException, ModifyNifException, NifExistingException {
        var existingPerson = personRepository.get(person.getNif()).orElseThrow(() -> new NotFoundException());

        Validator.of(person)
                .validate((p) -> !existingPerson.getNif().equals(p.getNif()), () -> new ModifyNifException())
                .validate(PhoneValidator::isValid, () -> new WrongPhoneException());

        return personRepository.save(person);
    }
}
