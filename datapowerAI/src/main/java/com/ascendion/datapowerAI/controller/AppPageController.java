package com.ascendion.datapowerAI.controller;

import com.ascendion.datapowerAI.entity.Page;
import com.ascendion.datapowerAI.service.AppPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/projects/{projectId}/apps/{appId}/pages")
@RequiredArgsConstructor
public class AppPageController {

    private final AppPageService appPageService;

    // ✅ Create multiple pages under specific project-app mapping
    @PostMapping
    public ResponseEntity<List<Page>> createPages(
            @PathVariable("projectId") UUID projectId,
            @PathVariable("appId") UUID appId,
            @RequestBody List<Page> pages) {
        return ResponseEntity.ok(appPageService.createPages(projectId, appId, pages));
    }

    // ✅ Get all pages under a specific project-app mapping
    @GetMapping
    public ResponseEntity<List<Page>> getPagesByProjectAndApp(
            @PathVariable("projectId") UUID projectId,
            @PathVariable("appId") UUID appId) {
        return ResponseEntity.ok(appPageService.getPagesByProjectAndApp(projectId, appId));
    }

    // ✅ Update a page
    @PutMapping("/{pageId}")
    public ResponseEntity<Page> updatePage(
            @PathVariable("pageId") UUID pageId,
            @RequestBody Page page) {
        return ResponseEntity.ok(appPageService.updatePage(pageId, page));
    }

    // ✅ Delete a page
    @DeleteMapping("/{pageId}")
    public ResponseEntity<Void> deletePage(@PathVariable("pageId") UUID pageId) {
        appPageService.deletePage(pageId);
        return ResponseEntity.noContent().build();
    }
}

