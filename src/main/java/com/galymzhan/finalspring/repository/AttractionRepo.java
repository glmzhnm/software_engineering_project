package com.galymzhan.finalspring.repository;

import com.galymzhan.finalspring.entity.TouristAttractionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttractionRepo extends JpaRepository<TouristAttractionEntity, Long> {
}
