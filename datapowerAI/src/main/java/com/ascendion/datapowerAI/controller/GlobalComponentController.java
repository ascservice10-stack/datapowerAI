package com.ascendion.datapowerAI.controller;

import com.ascendion.datapowerAI.entity.GlobalComponent;
import com.ascendion.datapowerAI.service.GlobalComponentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/projects/{projectId}/apps/{appId}/global-components")
@RequiredArgsConstructor
public class GlobalComponentController {

    private final GlobalComponentService globalComponentService;

    @PostMapping
    public ResponseEntity<GlobalComponent> createGlobalComponent(
            @PathVariable("projectId") UUID projectId,
            @PathVariable("appId") UUID appId,
            @RequestBody GlobalComponent component) {
        return ResponseEntity.ok(globalComponentService.createGlobalComponent(projectId, appId, component));
    }

    @GetMapping
    public ResponseEntity<List<GlobalComponent>> getGlobalComponents(
            @PathVariable("projectId") UUID projectId,
            @PathVariable("appId") UUID appId) {
        return ResponseEntity.ok(globalComponentService.getGlobalComponents(projectId, appId));
    }

    @PutMapping("/{globalComponentId}")
    public ResponseEntity<GlobalComponent> updateGlobalComponent(
            @PathVariable("globalComponentId") UUID globalComponentId,
            @RequestBody GlobalComponent component) {
        return ResponseEntity.ok(globalComponentService.updateGlobalComponent(globalComponentId, component));
    }

    @DeleteMapping("/{globalComponentId}")
    public ResponseEntity<Void> deleteGlobalComponent(@PathVariable("globalComponentId") UUID globalComponentId) {
        globalComponentService.deleteGlobalComponent(globalComponentId);
        return ResponseEntity.noContent().build();
    }
}
