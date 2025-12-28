package com.galymzhan.finalspring.service;

import com.galymzhan.finalspring.dto.CityDto;
import java.util.List;

public interface CityService {
    List<CityDto> getAll();
    CityDto getById(Long id);
    CityDto createCity(CityDto cityDto);
    CityDto updateCity(Long id, CityDto cityDto);
    boolean delete(Long id);
}