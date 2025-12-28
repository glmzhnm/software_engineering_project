package com.galymzhan.finalspring;

import com.galymzhan.finalspring.dto.AttractionDto;
import com.galymzhan.finalspring.dto.CityDto;
import com.galymzhan.finalspring.dto.CountryDto;
import com.galymzhan.finalspring.service.AttractionService;
import com.galymzhan.finalspring.service.CityService;
import com.galymzhan.finalspring.service.CountryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest(properties = {
        "spring.datasource.url=jdbc:postgresql://localhost:5433/testdb",
        "spring.datasource.username=postgres",
        "spring.datasource.password=postgres",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.liquibase.enabled=false",
        "spring.docker.compose.enabled=false"
})
public class AttractionServiceTest {

    @Autowired
    private AttractionService attractionService;
    @Autowired
    private CityService cityService;
    @Autowired
    private CountryService countryService;

    private Long createTestCity() {
        CountryDto country = new CountryDto(null, "C-" + UUID.randomUUID(), 100, "Cont");
        Long countryId = countryService.createCountry(country).getId();

        CityDto city = CityDto.builder()
                .nameDto("City-" + UUID.randomUUID())
                .populationDto(5000)
                .countryId(countryId)
                .build();
        return cityService.createCity(city).getId();
    }

    @Test
    void createAndGetTest() {
        Long cityId = createTestCity();

        AttractionDto dto = new AttractionDto();
        dto.setNameDto("Central Park");
        dto.setTypeDto("Park");
        dto.setTicketPriceDto(0.0);
        dto.setCityId(cityId);

        AttractionDto created = attractionService.addAttraction(dto);
        Assertions.assertNotNull(created.getId());

        AttractionDto found = attractionService.getById(created.getId());
        Assertions.assertEquals("Central Park", found.getNameDto());
        Assertions.assertEquals(cityId, found.getCityId());
    }

    @Test
    void updateTest() {
        Long cityId = createTestCity();
        AttractionDto dto = new AttractionDto();
        dto.setNameDto("Old Museum");
        dto.setCityId(cityId);
        dto.setTicketPriceDto(100.0);

        AttractionDto created = attractionService.addAttraction(dto);

        created.setNameDto("New Museum");
        AttractionDto updated = attractionService.updateAttraction(created.getId(), created);

        Assertions.assertEquals("New Museum", updated.getNameDto());
    }

    @Test
    void deleteTest() {
        Long cityId = createTestCity();
        AttractionDto dto = new AttractionDto();
        dto.setNameDto("Delete Me");
        dto.setCityId(cityId);
        dto.setTicketPriceDto(100.0);

        AttractionDto created = attractionService.addAttraction(dto);

        attractionService.delete(created.getId());

        try {
            attractionService.getById(created.getId());
            Assertions.fail("Should not be found");
        } catch (Exception e) {
            // Expected
        }
    }
}