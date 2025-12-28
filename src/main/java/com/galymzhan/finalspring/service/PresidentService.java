package com.galymzhan.finalspring.service;

import com.galymzhan.finalspring.dto.PresidentDto;
import java.util.List;

public interface PresidentService {
    List<PresidentDto> getAll();
    PresidentDto getById(Long id);
    PresidentDto addPresident(PresidentDto dto);
    PresidentDto updatePresident(Long id, PresidentDto dto);
    void delete(Long id);
}