package com.galymzhan.finalspring.service;

import com.galymzhan.finalspring.dto.NationDto;
import java.util.List;

public interface NationService {
    List<NationDto> getAll();
    NationDto getById(Long id);
    NationDto createNation(NationDto nationDto);
    NationDto updateNation(Long id, NationDto nationDto);
    boolean delete(Long id);
}
