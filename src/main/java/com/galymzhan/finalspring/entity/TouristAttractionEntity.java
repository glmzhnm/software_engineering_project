package com.galymzhan.finalspring.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "attractions")
@Data
public class TouristAttractionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private Double ticketPrice;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityEntity city;
}