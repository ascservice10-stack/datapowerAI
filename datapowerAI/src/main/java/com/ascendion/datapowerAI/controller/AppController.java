package com.ascendion.datapowerAI.controller;

import com.ascendion.datapowerAI.entity.App;
import com.ascendion.datapowerAI.service.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apps")
@RequiredArgsConstructor
public class AppController {

    private final AppService appService;

    // Create App
    @PostMapping("/create")
    public App createApp(@RequestBody App app) {
        return appService.createApp(app);
    }

    // Get All Apps
    @GetMapping
    public List<App> getAllApps() {
        return appService.getAllApps();
    }
}
