package io.gongarce.ud2_mvc.application;

import com.google.inject.Inject;
import io.gongarce.ud2_mvc.domain.person.Person;
import io.gongarce.ud2_mvc.domain.person.PersonRepository;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Gonzalo
 */
@RequiredArgsConstructor(onConstructor = @__({
    @Inject}))
public class GetPersonsUserCase implements UseCase {

    private final PersonRepository personRepository;

    public List<Person> get(@NonNull Optional<String> mail) {
        return mail
                .map(personRepository::getByMail)
                .orElseGet(personRepository::getAll);
    }
}
