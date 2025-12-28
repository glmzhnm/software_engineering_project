package com.galymzhan.finalspring.service.impl;

import com.galymzhan.finalspring.dto.AttractionDto;
import com.galymzhan.finalspring.entity.CityEntity;
import com.galymzhan.finalspring.entity.TouristAttractionEntity;
import com.galymzhan.finalspring.mapper.AttractionMapper;
import com.galymzhan.finalspring.repository.AttractionRepo;
import com.galymzhan.finalspring.repository.CityRepo;
import com.galymzhan.finalspring.service.AttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {

    private final AttractionRepo attractionRepo;
    private final CityRepo cityRepo;
    private final AttractionMapper attractionMapper;

    @Override
    public List<AttractionDto> getAll() {
        return attractionMapper.toDtoList(attractionRepo.findAll());
    }

    @Override
    public AttractionDto getById(Long id) {
        TouristAttractionEntity entity = attractionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Attraction not found"));
        return attractionMapper.toDto(entity);
    }

    @Override
    @Transactional
    public AttractionDto addAttraction(AttractionDto dto) {
        if (dto.getTicketPriceDto() < 0) {
            throw new RuntimeException("Price cannot be negative");
        }

        TouristAttractionEntity entity = attractionMapper.toEntity(dto);
        CityEntity city = cityRepo.findById(dto.getCityId())
                .orElseThrow(() -> new RuntimeException("City not found"));

        entity.setCity(city);
        return attractionMapper.toDto(attractionRepo.save(entity));
    }

    @Override
    @Transactional
    public AttractionDto updateAttraction(Long id, AttractionDto dto) {
        TouristAttractionEntity entity = attractionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Attraction not found"));

        if (dto.getTicketPriceDto() < 0) {
            throw new RuntimeException("Price cannot be negative");
        }

        entity.setName(dto.getNameDto());
        entity.setType(dto.getTypeDto());
        entity.setTicketPrice(dto.getTicketPriceDto());

        if (dto.getCityId() != null) {
            CityEntity city = cityRepo.findById(dto.getCityId())
                    .orElseThrow(() -> new RuntimeException("City not found"));
            entity.setCity(city);
        }

        return attractionMapper.toDto(attractionRepo.save(entity));
    }

    @Override
    public void delete(Long id) {
        if (!attractionRepo.existsById(id)) {
            throw new RuntimeException("Attraction not found");
        }
        attractionRepo.deleteById(id);
    }
}