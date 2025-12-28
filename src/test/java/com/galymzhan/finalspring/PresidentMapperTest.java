package com.galymzhan.finalspring;

import com.galymzhan.finalspring.dto.PresidentDto;
import com.galymzhan.finalspring.entity.CountryEntity;
import com.galymzhan.finalspring.entity.PresidentEntity;
import com.galymzhan.finalspring.mapper.PresidentMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PresidentMapperTest {

    @Autowired
    private PresidentMapper mapper;

    @Test
    void toDtoTest() {
        CountryEntity country = new CountryEntity();
        country.setId(99L);

        PresidentEntity entity = new PresidentEntity();
        entity.setId(1L);
        entity.setName("Tokayev");
        entity.setCountry(country);

        PresidentDto dto = mapper.toDto(entity);

        Assertions.assertEquals("Tokayev", dto.getNameDto());
        Assertions.assertEquals(99L, dto.getCountryId());
    }

    @Test
    void toEntityTest() {
        PresidentDto dto = new PresidentDto();
        dto.setNameDto("Biden");
        dto.setCountryId(55L);

        PresidentEntity entity = mapper.toEntity(dto);

        Assertions.assertEquals("Biden", entity.getName());
        Assertions.assertEquals(55L, entity.getCountry().getId());
    }
}