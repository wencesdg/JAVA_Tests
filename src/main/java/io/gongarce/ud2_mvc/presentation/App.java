package io.gongarce.ud2_mvc.presentation;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.gongarce.ud2_mvc.application.UseCase;
import io.gongarce.ud2_mvc.application.UseCaseModule;
import io.gongarce.ud2_mvc.infra.RepositoryModule;
import java.util.Objects;

/**
 *
 * @author Gonzalo
 */
public class App {

    private static App instance = null;

    private final Injector injector;

    private App() {
        injector = Guice.createInjector(new RepositoryModule(), new UseCaseModule());
    }

    private static App getInstance() {
        if (Objects.isNull(App.instance)) {
            App.instance = new App();
        }
        return instance;
    }

    public static <T extends UseCase> T get(Class<T> useCaseClass) {
        return getInstance().getUseCase(useCaseClass);
    }
    
    private <T extends UseCase> T getUseCase(Class<T> useCaseClass) {
        return injector.getInstance(useCaseClass);
    }
}
