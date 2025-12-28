package com.galymzhan.finalspring;

import com.galymzhan.finalspring.dto.AttractionDto;
import com.galymzhan.finalspring.entity.CityEntity;
import com.galymzhan.finalspring.entity.TouristAttractionEntity;
import com.galymzhan.finalspring.mapper.AttractionMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

public class AttractionMapperTest {
    private final AttractionMapper mapper = Mappers.getMapper(AttractionMapper.class);
    @Test
    void toDtoTest() {
        CityEntity city = new CityEntity();
        city.setId(10L);

        TouristAttractionEntity entity = new TouristAttractionEntity();
        entity.setId(1L);
        entity.setName("Medeu");
        entity.setTicketPrice(5000.0);
        entity.setCity(city);

        AttractionDto dto = mapper.toDto(entity);

        Assertions.assertEquals("Medeu", dto.getNameDto());
        Assertions.assertEquals(5000.0, dto.getTicketPriceDto());
        Assertions.assertEquals(10L, dto.getCityId());
    }

    @Test
    void toEntityTest() {
        AttractionDto dto = new AttractionDto();
        dto.setNameDto("Shymbulak");
        dto.setCityId(20L);

        TouristAttractionEntity entity = mapper.toEntity(dto);

        Assertions.assertEquals("Shymbulak", entity.getName());
        Assertions.assertEquals(20L, entity.getCity().getId());
    }
}