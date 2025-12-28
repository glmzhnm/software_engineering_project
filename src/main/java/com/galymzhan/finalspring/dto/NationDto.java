package com.galymzhan.finalspring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NationDto {
    private Long id;
    private String nameDto;
    private String descriptionDto;
}
