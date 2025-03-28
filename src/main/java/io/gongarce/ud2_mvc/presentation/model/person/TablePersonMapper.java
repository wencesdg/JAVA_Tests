package io.gongarce.ud2_mvc.presentation.model.person;

import io.gongarce.ud2_mvc.domain.person.Person;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author Gonzalo
 */
@Mapper
public interface TablePersonMapper {
    
    public static TablePersonMapper INSTANCE = Mappers.getMapper(TablePersonMapper.class);
    
    TablePerson toView(Person person);

    List<TablePerson> toView(List<Person> person);
}
