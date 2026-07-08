package com.ekos.backend.service;

import com.ekos.discovery.model.DiscoveryResult;
import com.ekos.insights.ArchitectureAnalyzer;
import com.ekos.scanner.ScannerEngine;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
public class ScanService {

    private final ScannerEngine scannerEngine;

    private final ArchitectureAnalyzer architectureAnalyzer;

    public ScanService(ScannerEngine scannerEngine, ArchitectureAnalyzer architectureAnalyzer) {
        this.scannerEngine = scannerEngine;
        this.architectureAnalyzer = architectureAnalyzer;
    }

    public DiscoveryResult scan(String path) {
        DiscoveryResult result =
                scannerEngine.scan(Path.of(path));
        architectureAnalyzer.analyze(result);

        return result;

    }

}