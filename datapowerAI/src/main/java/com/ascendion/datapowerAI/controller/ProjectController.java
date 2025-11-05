package com.ascendion.datapowerAI.controller;

import com.ascendion.datapowerAI.entity.App;
import com.ascendion.datapowerAI.entity.Project;
import com.ascendion.datapowerAI.service.AppService;
import com.ascendion.datapowerAI.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final AppService appService;

    // 🟢 Create Project - userId extracted from JWT
    @PostMapping("/create")
    public Project createProject(@RequestBody Project project) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); // or extract userId claim via JwtUtil
        return projectService.createProject(project, username);
    }

    // 🟢 Get Projects of Logged-in User
    @GetMapping
    public List<Project> getUserProjects() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return projectService.getProjectsByUser(username);
    }

    // 🟢 Create App
    @PostMapping("/app")
    public App createApp(@RequestBody App app) {
        return appService.createApp(app);
    }

    // 🟢 Get All Apps
    @GetMapping("/app")
    public List<App> getAllApps() {
        return appService.getAllApps();
    }

    // 🟢 Map Project to App
    @PostMapping("/{projectId}/map-app/{appId}")
    public void mapProjectToApp(@PathVariable("projectId") UUID projectId, @PathVariable("appId") UUID appId) {
        projectService.mapProjectToApp(projectId, appId);
    }

    // 🟢 Get Project Details (with Apps)
    @GetMapping("/{projectId}/details")
    public Project getProjectDetails(@PathVariable("projectId") UUID projectId) {
        return projectService.getProjectDetails(projectId);
    }
}