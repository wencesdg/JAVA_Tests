package io.gongarce.ud2_mvc.domain.mail;

import lombok.NonNull;
import lombok.Value;

/**
 *
 * @author Gonzalo
 */
@Value
public class Mail {
    @NonNull Long id;
    @NonNull String address;
    @NonNull Long personId;
}
