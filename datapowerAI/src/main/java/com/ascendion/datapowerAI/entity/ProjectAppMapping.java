package com.ascendion.datapowerAI.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "project_app_mapping")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectAppMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    @JsonBackReference("project-appMappings")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_id", nullable = false)
    @JsonIgnoreProperties({"projectMappings", "hibernateLazyInitializer", "handler"})
    private App app;

    @OneToMany(mappedBy = "projectAppMapping", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("projectAppMapping-pages")
    private List<Page> pages;

}
