package com.galymzhan.finalspring;

import com.galymzhan.finalspring.dto.NationDto;
import com.galymzhan.finalspring.service.NationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest(properties = {
        "spring.flyway.enabled=false",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
public class NationServiceTest {
    @Autowired
    private NationService nationService;

    @Test
    void getAllTest(){
        List<NationDto> nationDtoList = nationService.getAll();

        Assertions.assertNotNull(nationDtoList);
        Assertions.assertNotEquals(0, nationDtoList.size());

        for (int i = 0; i < nationDtoList.size(); i++) {
            NationDto nationDto = nationDtoList.get(i);

            Assertions.assertNotNull(nationDto);

            Assertions.assertNotNull(nationDto.getId());
            Assertions.assertNotNull(nationDto.getNameDto());
            Assertions.assertNotNull(nationDto.getDescriptionDto());
        }
    }

    @Test
    void getByIdTest(){
        Random random = new Random();
        int randomIndex = random.nextInt(nationService.getAll().size());

        Long someIndex = nationService.getAll().get(randomIndex).getId();

        NationDto nationDto = nationService.getById(someIndex);

        Assertions.assertNotNull(nationDto);

        Assertions.assertNotNull(nationDto.getId());
        Assertions.assertNotNull(nationDto.getNameDto());
        Assertions.assertNotNull(nationDto.getDescriptionDto());
    }

    @Test
    void addTest(){
        NationDto nationDto = new NationDto();
        nationDto.setNameDto("kazakh");
        nationDto.setDescriptionDto("Qwertyuiasdfghjk xcvb");

        NationDto add = nationService.createNation(nationDto);

        Assertions.assertNotNull(add);
        Assertions.assertNotNull(add.getId());
        Assertions.assertNotNull(add.getNameDto());
        Assertions.assertNotNull(add.getDescriptionDto());

        NationDto added = nationService.getById(add.getId());

        Assertions.assertNotNull(added);
        Assertions.assertNotNull(added.getId());
        Assertions.assertNotNull(added.getNameDto());
        Assertions.assertNotNull(added.getDescriptionDto());

        Assertions.assertEquals(add.getId(), added.getId());
        Assertions.assertEquals(add.getNameDto(), added.getNameDto());
        Assertions.assertEquals(add.getDescriptionDto(), added.getDescriptionDto());
    }

    @Test
    void updateTest(){
        Random random = new Random();
        int randomIndex = random.nextInt(nationService.getAll().size());
        Long someIndex = nationService.getAll().get(randomIndex).getId();

        NationDto newNation = NationDto
                .builder()
                .id(someIndex)
                .nameDto("ozbek")
                .descriptionDto("qwertyuiasdfghj")
                .build();

        NationDto update = nationService.updateNation(newNation.getId(), newNation);

        Assertions.assertNotNull(update);
        Assertions.assertNotNull(update.getId());
        Assertions.assertNotNull(update.getNameDto());
        Assertions.assertNotNull(update.getDescriptionDto());

        Assertions.assertEquals(newNation.getId(), update.getId());
        Assertions.assertEquals(newNation.getNameDto(), update.getNameDto());
        Assertions.assertEquals(newNation.getDescriptionDto(), update.getDescriptionDto());

        NationDto updatedNation = nationService.getById(someIndex);

        Assertions.assertEquals(update.getId(), updatedNation.getId());
        Assertions.assertEquals(update.getNameDto(), updatedNation.getNameDto());
        Assertions.assertEquals(update.getDescriptionDto(), updatedNation.getDescriptionDto());
    }


    @Test
    void deleteTest(){
        Random random = new Random();

        int randomIndex = random.nextInt(nationService.getAll().size());

        Long someIndex = nationService.getAll().get(randomIndex).getId();
        Assertions.assertTrue(nationService.delete(someIndex));

        NationDto deleted = nationService.getById(someIndex);

        Assertions.assertNull(deleted);

    }
}
