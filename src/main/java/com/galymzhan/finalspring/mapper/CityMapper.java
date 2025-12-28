package com.galymzhan.finalspring.mapper;

import com.galymzhan.finalspring.dto.CityDto;
import com.galymzhan.finalspring.entity.CityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;


@Mapper(componentModel = "spring", uses = {NationMapper.class, AttractionMapper.class})
public interface CityMapper {

    @Mapping(target = "nameDto", source = "name")
    @Mapping(target = "populationDto", source = "population")
    @Mapping(target = "countryId", source = "country.id")
    @Mapping(target = "nations", source = "nations")
    @Mapping(target = "attractions", source = "attractions") // Явно указываем
    CityDto toDto(CityEntity entity);

    @Mapping(target = "name", source = "nameDto")
    @Mapping(target = "population", source = "populationDto")
    @Mapping(target = "country.id",  source = "countryId")
    @Mapping(target = "nations",     source = "nations")
    @Mapping(target = "attractions", ignore = true)
    CityEntity toEntity(CityDto dto);

    List<CityDto> toDtoList(List<CityEntity> entities);
}

