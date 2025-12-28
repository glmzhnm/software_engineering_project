package com.galymzhan.finalspring.mapper;

import com.galymzhan.finalspring.dto.NationDto;
import com.galymzhan.finalspring.entity.NationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NationMapper {
    @Mapping(target = "nameDto", source = "name")
    @Mapping(target = "descriptionDto", source = "description")
    NationDto toDto(NationEntity entity);

    @Mapping(target = "name", source = "nameDto")
    @Mapping(target = "description", source = "descriptionDto")
    @Mapping(target = "cities", ignore = true)
    NationEntity toEntity(NationDto dto);

    List<NationDto> toDtoList(List<NationEntity> entities);
}