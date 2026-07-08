package com.ekos.backend.service;

import com.ekos.discovery.model.DiscoveryResult;
import com.ekos.insights.ArchitectureAnalyzer;
import com.ekos.insights.HealthScoreCalculator;
import com.ekos.insights.ProjectSummaryGenerator;
import com.ekos.scanner.ScannerEngine;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
public class ScanService {

    private final ProjectSummaryGenerator projectSummaryGenerator;

    private final HealthScoreCalculator healthScoreCalculator;

    private final ScannerEngine scannerEngine;

    private final ArchitectureAnalyzer architectureAnalyzer;

    public ScanService(ProjectSummaryGenerator projectSummaryGenerator, HealthScoreCalculator healthScoreCalculator, ScannerEngine scannerEngine, ArchitectureAnalyzer architectureAnalyzer) {
        this.projectSummaryGenerator = projectSummaryGenerator;
        this.healthScoreCalculator = healthScoreCalculator;
        this.scannerEngine = scannerEngine;
        this.architectureAnalyzer = architectureAnalyzer;
    }

    public DiscoveryResult scan(String path) {
        DiscoveryResult result =
                scannerEngine.scan(Path.of(path));
        architectureAnalyzer.analyze(result);
        healthScoreCalculator.calculate(result);
        projectSummaryGenerator.generate(result);
        return result;

    }

}