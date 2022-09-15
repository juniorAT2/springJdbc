package it.accenture.mapstruct;

import it.accenture.dto.PersonDto;
import it.accenture.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "id", source = "id")
    PersonDto toPersonDTO(Person person);
    @Mapping(target = "id", source = "id")
    Person fromPersonDto(PersonDto personDto);

    List<PersonDto> toPersonOptionsDTO(List<Person> personOptions);

}
