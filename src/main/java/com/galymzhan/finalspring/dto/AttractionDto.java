package com.galymzhan.finalspring.dto;
import lombok.Data;

@Data
public class AttractionDto {
    private Long id;
    private String nameDto;
    private String typeDto;
    private Double ticketPriceDto;
    private Long cityId;
}
