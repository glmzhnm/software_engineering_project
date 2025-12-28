package com.galymzhan.finalspring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CityDto {
    private Long id;
    private String nameDto;
    private Integer populationDto;
    private Long countryId;
    private List<NationDto> nations;
    private List<AttractionDto> attractions;
}

