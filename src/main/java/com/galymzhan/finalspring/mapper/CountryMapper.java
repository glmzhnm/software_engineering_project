package com.galymzhan.finalspring.mapper;

import com.galymzhan.finalspring.dto.CountryDto;
import com.galymzhan.finalspring.entity.CountryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    @Mapping(target = "nameDto", source = "name")
    @Mapping(target = "populationDto", source = "population")
    CountryDto toDto(CountryEntity entity);

    @Mapping(target = "name", source = "nameDto")
    @Mapping(target = "population", source = "populationDto")
    @Mapping(target = "cities", ignore = true)
    @Mapping(target = "president", ignore = true)
    CountryEntity toEntity(CountryDto dto);

    List<CountryDto> toDtoList(List<CountryEntity> entities);
}

