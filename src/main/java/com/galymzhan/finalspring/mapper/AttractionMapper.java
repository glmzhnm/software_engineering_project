package com.galymzhan.finalspring.mapper;

import com.galymzhan.finalspring.dto.AttractionDto;
import com.galymzhan.finalspring.entity.TouristAttractionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AttractionMapper {

    @Mapping(target = "nameDto", source = "name")
    @Mapping(target = "typeDto", source = "type")
    @Mapping(target = "ticketPriceDto", source = "ticketPrice")
    @Mapping(target = "cityId", source = "city.id")
    AttractionDto toDto(TouristAttractionEntity entity);

    @Mapping(target = "name", source = "nameDto")
    @Mapping(target = "type", source = "typeDto")
    @Mapping(target = "ticketPrice", source = "ticketPriceDto")
    @Mapping(target = "city.id", source = "cityId")
    TouristAttractionEntity toEntity(AttractionDto dto);

    List<AttractionDto> toDtoList(List<TouristAttractionEntity> entities);
}