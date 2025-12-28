package com.galymzhan.finalspring.repository;

import com.galymzhan.finalspring.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepo extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByName(String name);
}
