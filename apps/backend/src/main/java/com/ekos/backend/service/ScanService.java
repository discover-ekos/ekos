package com.ekos.backend.service;

import com.ekos.discovery.model.DiscoveryResult;
import com.ekos.insights.*;
import com.ekos.scanner.ScannerEngine;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
public class ScanService {

    private final ProjectSummaryGenerator projectSummaryGenerator;

    private final HealthScoreCalculator healthScoreCalculator;

    private final ScannerEngine scannerEngine;

    private final ArchitectureAnalyzer architectureAnalyzer;

    private final DuplicateApiAnalyzer duplicateApiAnalyzer;

    private final MissingServiceAnalyzer missingServiceAnalyzer;

    private final MissingRepositoryAnalyzer missingRepositoryAnalyzer;

    private final ProjectStatisticsGenerator projectStatisticsGenerator;


    public ScanService(ProjectSummaryGenerator projectSummaryGenerator, HealthScoreCalculator healthScoreCalculator, ScannerEngine scannerEngine, ArchitectureAnalyzer architectureAnalyzer, DuplicateApiAnalyzer duplicateApiAnalyzer, MissingServiceAnalyzer missingServiceAnalyzer, MissingRepositoryAnalyzer missingRepositoryAnalyzer, ProjectStatisticsGenerator projectStatisticsGenerator) {
        this.projectSummaryGenerator = projectSummaryGenerator;
        this.healthScoreCalculator = healthScoreCalculator;
        this.scannerEngine = scannerEngine;
        this.architectureAnalyzer = architectureAnalyzer;
        this.duplicateApiAnalyzer = duplicateApiAnalyzer;
        this.missingServiceAnalyzer = missingServiceAnalyzer;
        this.missingRepositoryAnalyzer = missingRepositoryAnalyzer;
        this.projectStatisticsGenerator = projectStatisticsGenerator;
    }

    public DiscoveryResult scan(String path) {
        DiscoveryResult result =
                scannerEngine.scan(Path.of(path));
        architectureAnalyzer.analyze(result);
        healthScoreCalculator.calculate(result);
        projectSummaryGenerator.generate(result);
        duplicateApiAnalyzer.analyze(result);
        missingServiceAnalyzer.analyze(result);
        missingRepositoryAnalyzer.analyze(result);
        projectStatisticsGenerator.generate(result);
        return result;

    }

}