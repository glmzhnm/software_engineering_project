package com.galymzhan.finalspring;

import com.galymzhan.finalspring.dto.CountryDto;
import com.galymzhan.finalspring.entity.CountryEntity;
import com.galymzhan.finalspring.entity.PresidentEntity;
import com.galymzhan.finalspring.repository.CountryRepo;
import com.galymzhan.finalspring.repository.PresidentRepo;
import com.galymzhan.finalspring.service.CountryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class CountryServiceTest {

    @Autowired
    private CountryService countryService;

    @Autowired
    private CountryRepo countryRepo;

    @Autowired
    private PresidentRepo presidentRepo; // Нужен, чтобы проверить удаление президента

    private CountryDto createTestCountry() {
        CountryDto dto = CountryDto.builder()
                .nameDto("TestCountry")
                .continent("Asia")
                .populationDto(1_000_000)
                .build();

        return countryService.createCountry(dto);
    }

    @Test
    void getAllTest() {
        createTestCountry();
        List<CountryDto> list = countryService.getAll();
        Assertions.assertFalse(list.isEmpty());
    }

    @Test
    void getByIdTest() {
        CountryDto created = createTestCountry();
        CountryDto found = countryService.getById(created.getId());
        Assertions.assertNotNull(found);
        Assertions.assertEquals(created.getId(), found.getId());
    }

    @Test
    void addTest() {
        CountryDto dto = CountryDto.builder()
                .nameDto("France")
                .continent("Europe")
                .populationDto(67_000_000)
                .build();
        CountryDto created = countryService.createCountry(dto);
        Assertions.assertNotNull(created.getId());
    }

    @Test
    void updateTest() {
        CountryDto created = createTestCountry();

        CountryDto updateDto = CountryDto.builder()
                .id(created.getId())
                .nameDto("New Name")
                .continent("New Continent")
                .populationDto(500)
                .build();

        CountryDto updated = countryService.updateCountry(created.getId(), updateDto);
        Assertions.assertEquals("New Name", updated.getNameDto());
    }

    @Test
    void deleteTest() {
        CountryDto created = createTestCountry();
        countryService.delete(created.getId());

        Assertions.assertThrows(RuntimeException.class, () -> {
            countryService.getById(created.getId());
        });
    }


    @Test
    void deleteCascadeTest() {
        CountryDto countryDto = createTestCountry();
        Long countryId = countryDto.getId();

        CountryEntity countryEntity = countryRepo.findById(countryId).orElseThrow();

        PresidentEntity president = new PresidentEntity();
        president.setName("Test President");
        president.setPoliticalParty("Test Party");
        president.setTermStartYear(2020);
        president.setCountry(countryEntity);
        countryEntity.setPresident(president);

        presidentRepo.save(president);
        Long presidentId = president.getId();

        Assertions.assertNotNull(presidentId);
        Assertions.assertTrue(presidentRepo.existsById(presidentId));

        countryService.delete(countryId);

        Assertions.assertFalse(countryRepo.existsById(countryId));


        Assertions.assertFalse(presidentRepo.existsById(presidentId));
    }
}