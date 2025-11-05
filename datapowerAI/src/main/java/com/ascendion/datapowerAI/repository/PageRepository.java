package com.ascendion.datapowerAI.repository;

import com.ascendion.datapowerAI.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PageRepository extends JpaRepository<Page, UUID> {
    List<Page> findByProjectAppMapping_Project_IdAndProjectAppMapping_App_Id(UUID projectId, UUID appId);
}
