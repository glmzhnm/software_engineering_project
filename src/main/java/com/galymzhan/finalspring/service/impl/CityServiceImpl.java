package com.galymzhan.finalspring.service.impl;

import com.galymzhan.finalspring.dto.CityDto;
import com.galymzhan.finalspring.entity.CityEntity;
import com.galymzhan.finalspring.entity.CountryEntity;
import com.galymzhan.finalspring.entity.NationEntity;
import com.galymzhan.finalspring.entity.TouristAttractionEntity;
import com.galymzhan.finalspring.mapper.CityMapper;
import com.galymzhan.finalspring.repository.CityRepo;
import com.galymzhan.finalspring.repository.CountryRepo;
import com.galymzhan.finalspring.repository.NationRepo; // Не забудь импортировать!
import com.galymzhan.finalspring.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CityServiceImpl implements CityService {

    private final CityMapper cityMapper;
    private final CityRepo cityRepo;
    private final CountryRepo countryRepo;
    private final NationRepo nationRepo;

    @Override
    public List<CityDto> getAll() {
        return cityMapper.toDtoList(cityRepo.findAll());
    }

    @Override
    public CityDto getById(Long id) {
        CityEntity city = cityRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("City not found with id: " + id));
        return cityMapper.toDto(city);
    }

    @Override
    @Transactional
    public CityDto createCity(CityDto cityDto) {
        CityEntity cityEntity = cityMapper.toEntity(cityDto);

        if (cityDto.getCountryId() != null) {
            CountryEntity country = countryRepo.findById(cityDto.getCountryId())
                    .orElseThrow(() -> new RuntimeException("Country not found"));
            cityEntity.setCountry(country);
        }

        if (cityEntity.getNations() != null && !cityEntity.getNations().isEmpty()) {
            List<Long> nationIds = cityEntity.getNations().stream()
                    .map(NationEntity::getId)
                    .collect(Collectors.toList());

            List<NationEntity> nationsFromDb = nationRepo.findAllById(nationIds);
            cityEntity.setNations(new HashSet<>(nationsFromDb));
        }

        if (cityEntity.getAttractions() != null) {
            for (TouristAttractionEntity attraction : cityEntity.getAttractions()) {
                attraction.setCity(cityEntity);
            }
        }

        CityEntity saved = cityRepo.save(cityEntity);
        return cityMapper.toDto(saved);
    }

    @Override
    @Transactional
    public CityDto updateCity(Long id, CityDto cityDto) {
        CityEntity cityEntity = cityRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("City not found with id: " + id));

        if (cityDto != null) {
            cityEntity.setName(cityDto.getNameDto());
            cityEntity.setPopulation(cityDto.getPopulationDto());

            if (cityDto.getCountryId() != null) {
                CountryEntity country = countryRepo.findById(cityDto.getCountryId())
                        .orElseThrow(() -> new RuntimeException("Country not found"));
                cityEntity.setCountry(country);
            }

            CityEntity mappedEntity = cityMapper.toEntity(cityDto);
            if (mappedEntity.getNations() != null) {
                List<Long> nationIds = mappedEntity.getNations().stream()
                        .map(NationEntity::getId)
                        .collect(Collectors.toList());
                List<NationEntity> nationsFromDb = nationRepo.findAllById(nationIds);
                cityEntity.setNations(new HashSet<>(nationsFromDb));
            }
        }

        CityEntity saved = cityRepo.save(cityEntity);
        return cityMapper.toDto(saved);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if (cityRepo.existsById(id)) {
            cityRepo.deleteById(id);
            return true;
        }
        return false;
    }
}