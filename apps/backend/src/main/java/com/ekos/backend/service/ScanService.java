package com.ekos.backend.service;

import com.ekos.backend.dto.ScanRequest;
import com.ekos.backend.dto.ScanResponse;
import com.ekos.discovery.model.DiscoveryResult;
import com.ekos.discovery.model.Technology;
import com.ekos.scanner.ScannerEngine;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

@Service
public class ScanService {

    private final ScannerEngine scannerEngine;

    public ScanService(ScannerEngine scannerEngine) {
        this.scannerEngine = scannerEngine;
    }

    public ScanResponse scan(ScanRequest request) {

        // Execute scan through the engine
        DiscoveryResult discoveryResult =
                scannerEngine.scan(Path.of(request.getProjectPath()));

        // Build response
        ScanResponse response = new ScanResponse();

        File project = new File(request.getProjectPath());
        response.setProjectName(project.getName());

        List<String> technologies = discoveryResult.getTechnologies()
                .stream()
                .map(Technology::getName)
                .toList();

        response.setTechnologies(technologies);

        return response;
    }
}