package com.galymzhan.finalspring;

import com.galymzhan.finalspring.dto.CityDto;
import com.galymzhan.finalspring.dto.NationDto;
import com.galymzhan.finalspring.entity.CityEntity;
import com.galymzhan.finalspring.entity.CountryEntity;
import com.galymzhan.finalspring.entity.NationEntity;
import com.galymzhan.finalspring.mapper.CityMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
class CityMapperTest {

    @Autowired
    private CityMapper cityMapper;

    @Test
    void convertEntityToDtoTest(){
        CountryEntity country = new CountryEntity();
        country.setId(2L);

        NationEntity nation = new NationEntity();
        nation.setId(3L);
        nation.setName("kazakh");
        nation.setDescription("desc");

        Set<NationEntity> nations = new HashSet<>();
        nations.add(nation);

        CityEntity cityEntity = new CityEntity();
        cityEntity.setId(1L);
        cityEntity.setName("Almaty");
        cityEntity.setPopulation(2000000);
        cityEntity.setCountry(country);
        cityEntity.setNations(nations);
        cityEntity.setAttractions(new ArrayList<>()); // Инициализируем пустым списком

        CityDto cityDto = cityMapper.toDto(cityEntity);

        Assertions.assertNotNull(cityDto);
        Assertions.assertNotNull(cityDto.getId());
        Assertions.assertNotNull(cityDto.getNameDto());
        Assertions.assertNotNull(cityDto.getPopulationDto());
        Assertions.assertNotNull(cityDto.getCountryId());
        Assertions.assertNotNull(cityDto.getNations());
        // Проверяем, что список аттракционов тоже создался (пустой)
        Assertions.assertNotNull(cityDto.getAttractions());

        Assertions.assertEquals(cityEntity.getId(), cityDto.getId());
        Assertions.assertEquals(cityEntity.getName(), cityDto.getNameDto());
        Assertions.assertEquals(cityEntity.getPopulation(), cityDto.getPopulationDto());
        Assertions.assertEquals(cityEntity.getCountry().getId(), cityDto.getCountryId());
        Assertions.assertEquals(cityEntity.getNations().size(), cityDto.getNations().size());
    }

    @Test
    void convertDtoToEntityTest(){
        List<NationDto> nationDtos = new ArrayList<>();
        nationDtos.add(new NationDto(1L, "Kazakh", "desc"));

        // Используем 6-й аргумент для аттракционов (пустой список)
        CityDto cityDto = new CityDto(1L, "Astana", 1200000, 4L, nationDtos, new ArrayList<>());

        CityEntity cityEntity = cityMapper.toEntity(cityDto);

        Assertions.assertNotNull(cityEntity);
        Assertions.assertNotNull(cityEntity.getId());
        Assertions.assertNotNull(cityEntity.getName());
        Assertions.assertNotNull(cityEntity.getPopulation());
        Assertions.assertNotNull(cityEntity.getCountry());
        // Nations маппятся, attractions игнорируются при toEntity согласно мапперу
        Assertions.assertNotNull(cityEntity.getNations());

        Assertions.assertEquals(cityDto.getId(), cityEntity.getId());
        Assertions.assertEquals(cityDto.getNameDto(), cityEntity.getName());
        Assertions.assertEquals(cityDto.getPopulationDto(), cityEntity.getPopulation());
        Assertions.assertEquals(cityDto.getCountryId(), cityEntity.getCountry().getId());
    }

    @Test
    void convertEntityListToDtoListTest(){
        CountryEntity country = new CountryEntity();
        country.setId(1L);

        List<CityEntity> cityEntities = new ArrayList<>();

        CityEntity almaty = new CityEntity();
        almaty.setId(1L);
        almaty.setName("Almaty");
        almaty.setPopulation(2000000);
        almaty.setCountry(country);
        almaty.setNations(new HashSet<>());
        almaty.setAttractions(new ArrayList<>());

        CityEntity astana = new CityEntity();
        astana.setId(2L);
        astana.setName("Astana");
        astana.setPopulation(1200000);
        astana.setCountry(country);
        astana.setNations(new HashSet<>());
        astana.setAttractions(new ArrayList<>());

        cityEntities.add(almaty);
        cityEntities.add(astana);

        List<CityDto> cityDtos = cityMapper.toDtoList(cityEntities);

        Assertions.assertNotNull(cityDtos);
        Assertions.assertEquals(cityEntities.size(), cityDtos.size());

        for (int i = 0; i < cityEntities.size(); i++) {
            CityEntity entity = cityEntities.get(i);
            CityDto dto = cityDtos.get(i);

            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getNameDto());
            Assertions.assertNotNull(dto.getPopulationDto());
            Assertions.assertNotNull(dto.getCountryId());

            Assertions.assertEquals(entity.getId(), dto.getId());
            Assertions.assertEquals(entity.getName(), dto.getNameDto());
            Assertions.assertEquals(entity.getPopulation(), dto.getPopulationDto());
            Assertions.assertEquals(entity.getCountry().getId(), dto.getCountryId());
        }
    }
}