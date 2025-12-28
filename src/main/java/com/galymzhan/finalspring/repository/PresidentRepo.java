package com.galymzhan.finalspring.repository;

import com.galymzhan.finalspring.entity.PresidentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresidentRepo extends JpaRepository<PresidentEntity, Long> {
    boolean existsByCountryId(Long countryId);
}
