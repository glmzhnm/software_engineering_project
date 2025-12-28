package com.galymzhan.finalspring.dto;
import lombok.Data;

@Data
public class PresidentDto {
    private Long id;
    private String nameDto;
    private String politicalPartyDto;
    private Integer termStartYearDto;
    private Long countryId;
}