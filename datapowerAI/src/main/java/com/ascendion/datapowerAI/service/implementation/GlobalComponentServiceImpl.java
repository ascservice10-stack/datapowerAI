package com.ascendion.datapowerAI.service.implementation;

import com.ascendion.datapowerAI.entity.GlobalComponent;
import com.ascendion.datapowerAI.entity.ProjectAppMapping;
import com.ascendion.datapowerAI.exception.ResourceNotFoundException;
import com.ascendion.datapowerAI.repository.GlobalComponentRepository;
import com.ascendion.datapowerAI.repository.ProjectAppMappingRepository;
import com.ascendion.datapowerAI.service.GlobalComponentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GlobalComponentServiceImpl implements GlobalComponentService {

    private final GlobalComponentRepository globalComponentRepository;
    private final ProjectAppMappingRepository projectAppMappingRepository;

    @Override
    @Transactional
    public GlobalComponent createGlobalComponent(UUID projectId, UUID appId, GlobalComponent component) {
        ProjectAppMapping mapping = projectAppMappingRepository
                .findByProjectIdAndAppId(projectId, appId)
                .orElseThrow(() -> new ResourceNotFoundException("Project-App mapping not found"));

        component.setProjectAppMapping(mapping);
        component.setCreatedAt(LocalDateTime.now());
        component.setActive(true);

        return globalComponentRepository.save(component);
    }

    @Override
    public List<GlobalComponent> getGlobalComponents(UUID projectId, UUID appId) {
        ProjectAppMapping mapping = projectAppMappingRepository
                .findByProjectIdAndAppId(projectId, appId)
                .orElseThrow(() -> new ResourceNotFoundException("Project-App mapping not found"));

        return globalComponentRepository.findByProjectAppMappingId(mapping.getId());
    }

    @Override
    @Transactional
    public GlobalComponent updateGlobalComponent(UUID componentId, GlobalComponent updatedComponent) {
        GlobalComponent existing = globalComponentRepository.findById(componentId)
                .orElseThrow(() -> new ResourceNotFoundException("Global Component not found"));

        existing.setName(updatedComponent.getName());
        existing.setValue(updatedComponent.getValue());
        existing.setUpdatedAt(LocalDateTime.now());
        existing.setUpdatedBy(updatedComponent.getUpdatedBy());
        existing.setActive(updatedComponent.isActive());

        return globalComponentRepository.save(existing);
    }

    @Override
    @Transactional
    public void deleteGlobalComponent(UUID componentId) {
        if (!globalComponentRepository.existsById(componentId)) {
            throw new ResourceNotFoundException("Global Component not found");
        }
        globalComponentRepository.deleteById(componentId);
    }
}
