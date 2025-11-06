package com.ascendion.datapowerAI.repository;

import com.ascendion.datapowerAI.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
    Optional<Project> findById(UUID id);
    boolean existsByNameIgnoreCase(String name);
    List<Project> findByCreatedByUsername(String username);
}
