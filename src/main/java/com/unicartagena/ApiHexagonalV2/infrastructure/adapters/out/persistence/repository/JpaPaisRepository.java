package com.unicartagena.ApiHexagonalV2V2.infrastructure.adapters.out.persistence.repository;

import com.unicartagena.ApiHexagonalV2V2.infrastructure.adapters.out.persistence.entity.PaisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPaisRepository extends JpaRepository<PaisEntity, Long> {
}