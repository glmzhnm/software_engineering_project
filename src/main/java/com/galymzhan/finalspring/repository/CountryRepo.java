package com.galymzhan.finalspring.repository;

import com.galymzhan.finalspring.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepo extends JpaRepository<CountryEntity, Long> {
}
