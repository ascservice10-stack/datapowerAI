package com.ascendion.datapowerAI.service;

import com.ascendion.datapowerAI.entity.GlobalComponent;

import java.util.List;
import java.util.UUID;

public interface GlobalComponentService {
    GlobalComponent createGlobalComponent(UUID projectId, UUID appId, GlobalComponent component);
    List<GlobalComponent> getGlobalComponents(UUID projectId, UUID appId);
    GlobalComponent updateGlobalComponent(UUID componentId, GlobalComponent updatedComponent);
    void deleteGlobalComponent(UUID componentId);
}
