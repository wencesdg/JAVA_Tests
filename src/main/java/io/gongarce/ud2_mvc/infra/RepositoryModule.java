package io.gongarce.ud2_mvc.infra;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import io.gongarce.ud2_mvc.domain.person.PersonRepository;
import io.gongarce.ud2_mvc.infra.jdbc.JdbcPersonRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.Objects;

/**
 *
 * @author Gonzalo
 */
public class RepositoryModule extends AbstractModule {

    private EntityManagerFactory entityManagerFactory;
    
    @Override
    protected void configure() {
        bind(PersonRepository.class).to(JdbcPersonRepository.class);
    }

    @Provides
    @SuppressWarnings("unused")
    EntityManager provideEntityManager() {
        if (Objects.isNull(entityManagerFactory)) {
            entityManagerFactory = Persistence.createEntityManagerFactory("io.gongarce.h2");
        }
        return entityManagerFactory.createEntityManager();
    }
}
