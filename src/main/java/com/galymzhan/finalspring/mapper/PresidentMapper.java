package com.galymzhan.finalspring.mapper;

import com.galymzhan.finalspring.dto.PresidentDto;
import com.galymzhan.finalspring.entity.PresidentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PresidentMapper {

    @Mapping(target = "nameDto", source = "name")
    @Mapping(target = "politicalPartyDto", source = "politicalParty")
    @Mapping(target = "termStartYearDto", source = "termStartYear")
    @Mapping(target = "countryId", source = "country.id")
    PresidentDto toDto(PresidentEntity entity);

    @Mapping(target = "name", source = "nameDto")
    @Mapping(target = "politicalParty", source = "politicalPartyDto")
    @Mapping(target = "termStartYear", source = "termStartYearDto")
    @Mapping(target = "country.id", source = "countryId")
    PresidentEntity toEntity(PresidentDto dto);

    List<PresidentDto> toDtoList(List<PresidentEntity> entities);
}