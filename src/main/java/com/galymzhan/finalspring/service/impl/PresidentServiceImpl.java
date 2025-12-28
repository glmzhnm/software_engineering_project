package com.galymzhan.finalspring.service.impl;

import com.galymzhan.finalspring.dto.PresidentDto;
import com.galymzhan.finalspring.entity.CountryEntity;
import com.galymzhan.finalspring.entity.PresidentEntity;
import com.galymzhan.finalspring.mapper.PresidentMapper;
import com.galymzhan.finalspring.repository.CountryRepo;
import com.galymzhan.finalspring.repository.PresidentRepo;
import com.galymzhan.finalspring.service.PresidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PresidentServiceImpl implements PresidentService {

    private final PresidentRepo presidentRepo;
    private final CountryRepo countryRepo;
    private final PresidentMapper presidentMapper;

    @Override
    public List<PresidentDto> getAll() {
        return presidentMapper.toDtoList(presidentRepo.findAll());
    }

    @Override
    public PresidentDto getById(Long id) {
        PresidentEntity entity = presidentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("President not found"));
        return presidentMapper.toDto(entity);
    }

    @Override
    @Transactional
    public PresidentDto addPresident(PresidentDto dto) {
        CountryEntity country = countryRepo.findById(dto.getCountryId())
                .orElseThrow(() -> new RuntimeException("Country not found"));

        // Логика верная: одна страна - один президент
        if (presidentRepo.existsByCountryId(dto.getCountryId())) {
            throw new RuntimeException("This country already has a president!");
        }

        PresidentEntity entity = presidentMapper.toEntity(dto);
        entity.setCountry(country);
        return presidentMapper.toDto(presidentRepo.save(entity));
    }

    @Override
    @Transactional
    public PresidentDto updatePresident(Long id, PresidentDto dto) {
        PresidentEntity entity = presidentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("President not found"));

        entity.setName(dto.getNameDto());
        entity.setPoliticalParty(dto.getPoliticalPartyDto());
        entity.setTermStartYear(dto.getTermStartYearDto());

        if (dto.getCountryId() != null) {
            CountryEntity country = countryRepo.findById(dto.getCountryId())
                    .orElseThrow(() -> new RuntimeException("Country not found"));
            entity.setCountry(country);
        }

        return presidentMapper.toDto(presidentRepo.save(entity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!presidentRepo.existsById(id)) {
            throw new RuntimeException("President not found");
        }
        presidentRepo.deleteById(id);
    }
}