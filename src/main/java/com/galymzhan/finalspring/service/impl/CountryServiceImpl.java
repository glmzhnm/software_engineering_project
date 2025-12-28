package com.galymzhan.finalspring.service.impl;

import com.galymzhan.finalspring.dto.CountryDto;
import com.galymzhan.finalspring.entity.CountryEntity;
import com.galymzhan.finalspring.mapper.CountryMapper;
import com.galymzhan.finalspring.repository.CountryRepo;
import com.galymzhan.finalspring.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CountryServiceImpl implements CountryService {

    private final CountryMapper countryMapper;
    private final CountryRepo countryRepo;
    @Override
    public List<CountryDto> getAll() {
        return countryMapper.toDtoList(countryRepo.findAll());
    }
    @Override
    public CountryDto getById(Long id) {
        CountryEntity country = countryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found with id: " + id));
        return countryMapper.toDto(country);
    }
    @Override
    @Transactional
    public CountryDto createCountry(CountryDto countryDto) {
        CountryEntity entity = countryMapper.toEntity(countryDto);
        if(entity.getPresident() != null) {
            entity.getPresident().setCountry(entity);
        }
        return countryMapper.toDto(countryRepo.save(entity));
    }

    @Override
    @Transactional
    public CountryDto updateCountry(Long id, CountryDto countryDto) {
        CountryEntity updateCountryEntity = countryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found with id: " + id));

        if(countryDto != null){
            updateCountryEntity.setName(countryDto.getNameDto());
            updateCountryEntity.setPopulation(countryDto.getPopulationDto());
            updateCountryEntity.setContinent(countryDto.getContinent());
        }

        return countryMapper.toDto(countryRepo.save(updateCountryEntity));
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if(countryRepo.existsById(id)){
            countryRepo.deleteById(id);
            return true;
        }
        return false;
    }
}