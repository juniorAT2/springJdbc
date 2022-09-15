package it.accenture.mapstruct;

import it.accenture.dto.CityDto;
import it.accenture.model.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CityMapper {
    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    @Mapping(target="id", source="id")
    CityDto toCityDTO(City city);

    @Mapping(target="id", source="id")
    City fromCityDTO(CityDto cDto);

    List<CityDto> toCityOptionsDTO(List<City> cityOptions);
}
