package com.galymzhan.finalspring.service;

import com.galymzhan.finalspring.dto.AttractionDto;
import java.util.List;

public interface AttractionService {
    List<AttractionDto> getAll();
    AttractionDto getById(Long id);
    AttractionDto addAttraction(AttractionDto dto);
    AttractionDto updateAttraction(Long id, AttractionDto dto);
    void delete(Long id);
}
