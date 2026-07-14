package com.ekos.report;

import com.ekos.discovery.model.DiscoveryResult;
import com.ekos.report.analyzer.*;
import com.ekos.report.analyzer.model.AnalysisResult;
import com.ekos.report.health.HealthScoreCalculator;

public class AnalysisEngine {

    private final LayerViolationAnalyzer layerAnalyzer =
            new LayerViolationAnalyzer();

    private final CircularDependencyAnalyzer circularAnalyzer =
            new CircularDependencyAnalyzer();

    private final HealthScoreCalculator
            healthCalculator =
            new HealthScoreCalculator();

    private final DependencyHeatmapAnalyzer heatmapAnalyzer =
            new DependencyHeatmapAnalyzer();

    private final GodClassAnalyzer godClassAnalyzer =
            new GodClassAnalyzer();

    private final DeadCodeAnalyzer deadCodeAnalyzer =
            new DeadCodeAnalyzer();

    private final ArchitectureGraphAnalyzer graphAnalyzer =
            new ArchitectureGraphAnalyzer();

    public AnalysisResult analyze(DiscoveryResult result){

        AnalysisResult analysis = new AnalysisResult();

        layerAnalyzer.analyze(result);

        circularAnalyzer.analyze(result);

        healthCalculator.calculate(result);

        heatmapAnalyzer.analyze(result);

        godClassAnalyzer.analyze(result, analysis);

        deadCodeAnalyzer.analyze(result, analysis);

        graphAnalyzer.analyze(result, analysis);

        return analysis;
    }

}