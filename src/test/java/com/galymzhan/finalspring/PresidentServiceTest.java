package com.galymzhan.finalspring;

import com.galymzhan.finalspring.dto.CountryDto;
import com.galymzhan.finalspring.dto.PresidentDto;
import com.galymzhan.finalspring.repository.PresidentRepo;
import com.galymzhan.finalspring.service.CountryService;
import com.galymzhan.finalspring.service.PresidentService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

@SpringBootTest(properties = {
        "spring.datasource.url=jdbc:postgresql://localhost:5433/testdb",
        "spring.datasource.username=postgres",
        "spring.datasource.password=postgres",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.liquibase.enabled=false",
        "spring.docker.compose.enabled=false"
})
@Transactional
public class PresidentServiceTest {

    @Autowired
    private PresidentService presidentService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private EntityManager entityManager;
    private Long createUniqueCountry() {
        CountryDto country = CountryDto.builder()
                .nameDto("Country-" + UUID.randomUUID())
                .continent("Test")
                .populationDto(100)
                .build();
        return countryService.createCountry(country).getId();
    }

    @Test
    void createAndGetTest() {
        Long countryId = createUniqueCountry();

        PresidentDto dto = new PresidentDto();
        dto.setNameDto("President Test");
        dto.setPoliticalPartyDto("Party A");
        dto.setTermStartYearDto(2020);
        dto.setCountryId(countryId);

        PresidentDto created = presidentService.addPresident(dto);
        Assertions.assertNotNull(created.getId());

        PresidentDto found = presidentService.getById(created.getId());
        Assertions.assertEquals("President Test", found.getNameDto());
        Assertions.assertEquals(countryId, found.getCountryId());
    }

    @Test
    void getAllTest() {
        Long countryId = createUniqueCountry();
        PresidentDto dto = new PresidentDto();
        dto.setNameDto("All Test");
        dto.setCountryId(countryId);

        presidentService.addPresident(dto);
        List<PresidentDto> list = presidentService.getAll();
        Assertions.assertFalse(list.isEmpty());
    }

    @Test
    void updateTest() {
        Long countryId = createUniqueCountry();

        PresidentDto dto = new PresidentDto();
        dto.setNameDto("Old Name");
        dto.setCountryId(countryId);
        PresidentDto created = presidentService.addPresident(dto);

        created.setNameDto("New Name");
        PresidentDto updated = presidentService.updatePresident(created.getId(), created);

        Assertions.assertEquals("New Name", updated.getNameDto());
    }

    @Test
    void deleteTest() {
        Long countryId = createUniqueCountry();

        PresidentDto dto = new PresidentDto();
        dto.setNameDto("To Delete");
        dto.setCountryId(countryId);
        PresidentDto created = presidentService.addPresident(dto);
        Long id = created.getId();

        presidentService.delete(id);

        entityManager.flush();
        entityManager.clear();

        Assertions.assertThrows(RuntimeException.class, () -> {
            presidentService.getById(id);
        });
    }
}