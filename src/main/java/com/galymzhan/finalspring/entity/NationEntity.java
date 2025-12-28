package com.galymzhan.finalspring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "nations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToMany(mappedBy = "nations")
    private Set<CityEntity> cities = new HashSet<>();
}
