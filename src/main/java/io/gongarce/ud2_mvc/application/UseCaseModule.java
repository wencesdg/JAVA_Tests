package io.gongarce.ud2_mvc.application;

import com.google.inject.AbstractModule;

/**
 *
 * @author Gonzalo
 */
public class UseCaseModule extends AbstractModule {
    
    @Override
    protected void configure() {
        bind(GetPersonsUserCase.class).asEagerSingleton();
        bind(CreatePersonsUserCase.class).asEagerSingleton();
    }
}
