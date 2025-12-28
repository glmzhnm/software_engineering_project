package com.galymzhan.finalspring.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "presidents")
@Data
public class PresidentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String politicalParty;
    private Integer termStartYear;

    @OneToOne
    @JoinColumn(name = "country_id", unique = true)
    private CountryEntity country;
}