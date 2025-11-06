package com.ascendion.datapowerAI.service;

import com.ascendion.datapowerAI.entity.App;
import com.ascendion.datapowerAI.entity.Project;
import com.ascendion.datapowerAI.entity.ProjectAppMapping;
import com.ascendion.datapowerAI.entity.User;
import com.ascendion.datapowerAI.exception.BadRequestException;
import com.ascendion.datapowerAI.exception.DuplicateResourceException;
import com.ascendion.datapowerAI.exception.ResourceNotFoundException;
import com.ascendion.datapowerAI.repository.AppRepository;
import com.ascendion.datapowerAI.repository.ProjectAppMappingRepository;
import com.ascendion.datapowerAI.repository.ProjectRepository;
import com.ascendion.datapowerAI.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final AppRepository appRepository;
    private final ProjectAppMappingRepository projectAppMappingRepository;


    public Project createProject(Project project, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (project.getName() == null || project.getName().trim().isEmpty()) {
            throw new BadRequestException("Project name cannot be empty");
        }
        boolean exists = projectRepository.existsByNameIgnoreCase(project.getName());
        if (exists) {
            throw new DuplicateResourceException("Project with name '" + project.getName() + "' already exists.");
        }
        project.setCreatedBy(user);
        return projectRepository.save(project);
    }

    public List<Project> getProjectsByUser(String username) {
        return projectRepository.findByCreatedByUsername(username);
    }


    public void mapProjectToApp(UUID projectId, UUID appId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        App app = appRepository.findById(appId)
                .orElseThrow(() -> new ResourceNotFoundException("App not found"));

        ProjectAppMapping mapping = new ProjectAppMapping();
        mapping.setProject(project);
        mapping.setApp(app);
        projectAppMappingRepository.save(mapping);
    }

    public Project getProjectDetails(UUID projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
    }
}
