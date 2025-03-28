package io.gongarce.ud2_mvc.domain.person;

import io.gongarce.ud2_mvc.domain.mail.Mail;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;
import lombok.With;

/**
 *
 * @author Gonzalo
 */
@Value
public class Person {
    Long id;
    @NonNull String nif;
    @NonNull String name;
    @With String place;
    List<Mail> mails;
    List<String> phones;
}
