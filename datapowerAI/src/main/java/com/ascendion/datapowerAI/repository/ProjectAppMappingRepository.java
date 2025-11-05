package com.ascendion.datapowerAI.repository;

import com.ascendion.datapowerAI.entity.Project;
import com.ascendion.datapowerAI.entity.ProjectAppMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectAppMappingRepository extends JpaRepository<ProjectAppMapping, UUID> {
    List<ProjectAppMapping> findByProject(Project project);
    Optional<ProjectAppMapping> findByProjectIdAndAppId(UUID projectId, UUID appId);
}
