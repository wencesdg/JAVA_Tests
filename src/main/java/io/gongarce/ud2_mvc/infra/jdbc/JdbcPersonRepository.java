package io.gongarce.ud2_mvc.infra.jdbc;

import com.google.inject.Inject;
import io.gongarce.ud2_mvc.domain.person.Person;
import io.gongarce.ud2_mvc.domain.person.PersonRepository;
import io.gongarce.ud2_mvc.domain.person.error.NifExistingException;
import io.gongarce.ud2_mvc.domain.person.error.SavePersonException;
import io.gongarce.ud2_mvc.infra.jdbc.entities.PersonEntity;
import io.gongarce.ud2_mvc.infra.jdbc.mappers.PersonEntityMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Gonzalo
 */
@RequiredArgsConstructor(onConstructor = @__({
    @Inject}))
public class JdbcPersonRepository implements PersonRepository {

    private final EntityManager entityManager;

    @Override
    public Person save(Person person) throws SavePersonException, NifExistingException {
        entityManager.getTransaction().begin();
        PersonEntity entity = PersonEntityMapper.INSTANCE.toEntity(person);
        Long id = entity.getId();

        var existingNif = findByNif(person.getNif());
        if (existingNif.filter((p) -> !p.getId().equals(id)).isPresent()) {
            throw new NifExistingException();
        }
        try {
            entity = entityManager.merge(entity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            Logger.getLogger(JdbcPersonRepository.class.getName()).log(Level.SEVERE, null, e);
            throw new SavePersonException();
        }
        return PersonEntityMapper.INSTANCE.toDomain(entity);
    }

    @Override
    public Optional<Person> get(String nif) {
        return findByNif(nif).map(PersonEntityMapper.INSTANCE::toDomain);
    }

    @Override
    public List<Person> getAll() {
        List<PersonEntity> persons = entityManager.createNamedQuery("Person.findAll").getResultList();
        return PersonEntityMapper.INSTANCE.toDomain(persons);
    }

    @Override
    public List<Person> getByMail(String address) {
        Query query = entityManager.createNamedQuery("Person.findByMail");
        query.setParameter("mail", address);
        List<PersonEntity> persons = query.getResultList();
        return PersonEntityMapper.INSTANCE.toDomain(persons);
    }

    private Optional<PersonEntity> findByNif(String nif) {
        Query query = entityManager.createNamedQuery("Person.findByNif");
        query.setParameter("nif", nif);
        PersonEntity person = (PersonEntity) query.getSingleResultOrNull();
        return Optional.ofNullable(person);
    }
}
