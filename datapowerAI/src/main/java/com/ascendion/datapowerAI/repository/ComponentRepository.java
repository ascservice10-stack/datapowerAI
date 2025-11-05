package com.ascendion.datapowerAI.repository;

import com.ascendion.datapowerAI.entity.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ComponentRepository extends JpaRepository<Component, UUID> {
    List<Component> findByPageId(UUID pageId);
}
