package com.ascendion.datapowerAI.service.implementation;

import com.ascendion.datapowerAI.entity.Component;
import com.ascendion.datapowerAI.entity.Page;
import com.ascendion.datapowerAI.entity.ProjectAppMapping;
import com.ascendion.datapowerAI.exception.ResourceNotFoundException;
import com.ascendion.datapowerAI.repository.ComponentRepository;
import com.ascendion.datapowerAI.repository.PageRepository;
import com.ascendion.datapowerAI.repository.ProjectAppMappingRepository;
import com.ascendion.datapowerAI.service.AppPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppPageServiceImpl implements AppPageService {

    private final ProjectAppMappingRepository projectAppMappingRepository;
    private final PageRepository pageRepository;
    private final ComponentRepository componentRepository;

    @Override
    @Transactional
    public List<Page> createPages(UUID projectId, UUID appId, List<Page> pages) {

        ProjectAppMapping mapping = projectAppMappingRepository
                .findByProjectIdAndAppId(projectId, appId)
                .orElseThrow(() -> new ResourceNotFoundException("Project-App mapping not found"));

        for (Page page : pages) {
            page.setProjectAppMapping(mapping);
            page.setCreatedAt(LocalDateTime.now());
            page.setActive(true);

            if (page.getComponents() != null) {
                for (Component comp : page.getComponents()) {
                    comp.setPage(page);
                    comp.setCreatedAt(LocalDateTime.now());
                    comp.setActive(true);
                }
            }
        }

        return pageRepository.saveAll(pages);
    }

    @Override
    public List<Page> getPagesByProjectAndApp(UUID projectId, UUID appId) {
        return pageRepository.findByProjectAppMapping_Project_IdAndProjectAppMapping_App_Id(projectId, appId);
    }

    @Override
    @Transactional
    public Page updatePage(UUID pageId, Page updatedPage) {
        Page existing = pageRepository.findById(pageId)
                .orElseThrow(() -> new ResourceNotFoundException("Page not found"));

        existing.setName(updatedPage.getName());
        existing.setUpdatedAt(LocalDateTime.now());
        existing.setActive(updatedPage.isActive());

        return pageRepository.save(existing);
    }

    @Override
    @Transactional
    public void deletePage(UUID pageId) {
        if (!pageRepository.existsById(pageId)) {
            throw new ResourceNotFoundException("Page not found");
        }
        pageRepository.deleteById(pageId);
    }
}
