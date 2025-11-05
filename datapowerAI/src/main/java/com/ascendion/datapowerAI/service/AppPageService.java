package com.ascendion.datapowerAI.service;

import com.ascendion.datapowerAI.entity.Page;

import java.util.List;
import java.util.UUID;

public interface AppPageService {
    // Create multiple pages with components under one Project-App mapping
    List<Page> createPages(UUID projectId, UUID appId, List<Page> pages);

    // Get all pages (with components) for a specific project-app combination
    List<Page> getPagesByProjectAndApp(UUID projectId, UUID appId);

    // Update page
    Page updatePage(UUID pageId, Page updatedPage);

    // Delete page
    void deletePage(UUID pageId);
}
