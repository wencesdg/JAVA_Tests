package io.gongarce.ud2_mvc.application.validation;

import io.gongarce.ud2_mvc.domain.error.ValidationExpception;
import java.util.function.Function;
import java.util.function.Supplier;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Gonzalo
 * @param <T> Type of object to validate
 */
@RequiredArgsConstructor(staticName = "of")
public class Validator<T> {

    private final T object;
    private boolean hasFail = false;

    public <X extends Throwable> Validator<T> validate(
            @NonNull Function<T, Boolean> validator,
            @NonNull Supplier<? extends X> exceptionSupplier) throws X {
        if (!hasFail && !validator.apply(object)) {
            hasFail = true;
            throw exceptionSupplier.get();
        }
        return this;
    }

    public Validator<T> validate(@NonNull Function<T, Boolean> validator) throws ValidationExpception {
        if (!hasFail && !validator.apply(object)) {
            hasFail = true;
            throw new ValidationExpception();
        }
        return this;
    }

    public boolean isValid() {
        return !this.hasFail;
    }
}
