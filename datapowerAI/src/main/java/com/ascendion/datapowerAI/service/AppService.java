package com.ascendion.datapowerAI.service;

import com.ascendion.datapowerAI.entity.App;
import com.ascendion.datapowerAI.repository.AppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppService {

    private final AppRepository appRepository;

    public App createApp(App app) {
        app.setCreatedAt(LocalDateTime.now());
        app.setUpdatedAt(LocalDateTime.now());
        app.setActive(true);
        return appRepository.save(app);
    }

    public List<App> getAllApps() {
        return appRepository.findAll();
    }
}

