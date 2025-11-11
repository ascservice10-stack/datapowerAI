package com.ascendion.datapowerAI.repository;

import com.ascendion.datapowerAI.entity.GlobalComponent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GlobalComponentRepository extends JpaRepository<GlobalComponent, UUID> {
    List<GlobalComponent> findByProjectAppMappingId(UUID projectAppMappingId);
}