package com.galymzhan.finalspring;

import com.galymzhan.finalspring.dto.NationDto;
import com.galymzhan.finalspring.entity.NationEntity;
import com.galymzhan.finalspring.mapper.NationMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

public class NationMapperTest {
    private final NationMapper nationMapper = Mappers.getMapper(NationMapper.class);

    @Test
    void convertEntityToDtoTest(){
        NationEntity nationEntity = new NationEntity();
        nationEntity.setId(1L);
        nationEntity.setName("kazakh");
        nationEntity.setDescription("qwertyu");

        NationDto nationDto = nationMapper.toDto(nationEntity);

        Assertions.assertNotNull(nationDto);

        Assertions.assertNotNull(nationDto.getId());
        Assertions.assertNotNull(nationDto.getNameDto());
        Assertions.assertNotNull(nationDto.getDescriptionDto());

        Assertions.assertEquals(nationEntity.getId(), nationDto.getId());
        Assertions.assertEquals(nationEntity.getName(), nationDto.getNameDto());
        Assertions.assertEquals(nationEntity.getDescription(), nationDto.getDescriptionDto());
    }

    @Test
    void convertDtoToEntityTest(){
        NationDto nationDto = new NationDto(1L, "kazakh", "qwertyu");

        NationEntity nationEntity = nationMapper.toEntity(nationDto);

        Assertions.assertNotNull(nationEntity);

        Assertions.assertNotNull(nationEntity.getId());
        Assertions.assertNotNull(nationEntity.getName());
        Assertions.assertNotNull(nationEntity.getDescription());

        Assertions.assertEquals(nationDto.getId(), nationEntity.getId());
        Assertions.assertEquals(nationDto.getNameDto(), nationEntity.getName());
        Assertions.assertEquals(nationDto.getDescriptionDto(), nationEntity.getDescription());
    }

    @Test
    void convertEntityListToDtoListTest(){
        List<NationEntity> nationEntityList = new ArrayList<>();
        NationEntity kazakh = new NationEntity();
        kazakh.setId(1L);
        kazakh.setName("kazakh");
        kazakh.setDescription("qwertyu");

        NationEntity japan = new NationEntity();
        japan.setId(2L);
        japan.setName("japan");
        japan.setDescription("poiuytreqwe");

        NationEntity german = new NationEntity();
        german.setId(3L);
        german.setName("german");
        german.setDescription("xdcftvgybhnjxdcfvgh");

        nationEntityList.add(kazakh);
        nationEntityList.add(japan);
        nationEntityList.add(german);

        List<NationDto> nationDtoList = nationMapper.toDtoList(nationEntityList);

        Assertions.assertNotNull(nationDtoList);
        Assertions.assertNotEquals(0, nationDtoList.size());
        Assertions.assertEquals(nationEntityList.size(), nationDtoList.size());

        for (int i = 0; i < nationEntityList.size(); i++) {
            NationEntity nationEntity = nationEntityList.get(i);
            NationDto nationDto = nationDtoList.get(i);

            Assertions.assertNotNull(nationDto);

            Assertions.assertNotNull(nationDto.getId());
            Assertions.assertNotNull(nationDto.getNameDto());
            Assertions.assertNotNull(nationDto.getDescriptionDto());

            Assertions.assertEquals(nationEntity.getId(), nationDto.getId());
            Assertions.assertEquals(nationEntity.getName(), nationDto.getNameDto());
            Assertions.assertEquals(nationEntity.getDescription(), nationDto.getDescriptionDto());
        }
    }
}
