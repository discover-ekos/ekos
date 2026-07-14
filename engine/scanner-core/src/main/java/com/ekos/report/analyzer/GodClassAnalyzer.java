package com.ekos.report.analyzer;

import com.ekos.discovery.model.ComponentInfo;
import com.ekos.discovery.model.DiscoveryResult;
import com.ekos.report.analyzer.model.AnalysisResult;
import com.ekos.report.analyzer.model.GodClass;
import com.ekos.report.analyzer.model.JavaMetrics;

import java.util.List;

public class GodClassAnalyzer {

    private final JavaMetricsCollector collector =
            new JavaMetricsCollector();

    public void analyze(DiscoveryResult discovery,
                        AnalysisResult analysis) {

        analyzeComponents(
                discovery.getStructure().getControllers(),
                analysis);

        analyzeComponents(
                discovery.getStructure().getServices(),
                analysis);

        analyzeComponents(
                discovery.getStructure().getRepositories(),
                analysis);

    }

    private void analyzeComponents(List<ComponentInfo> components,
                                   AnalysisResult analysis) {

        for (ComponentInfo component : components) {

            JavaMetrics metrics =
                    collector.collect(component.getFilePath());

            int score = calculateScore(metrics);

            if (score < 60) {
                continue;
            }

            GodClass god = new GodClass();

            god.setClassName(metrics.getClassName());

            god.setMethodCount(metrics.getMethodCount());

            god.setFieldCount(metrics.getFieldCount());

            god.setDependencyCount(metrics.getDependencyCount());

            god.setScore(score);

            analysis.getGodClasses().add(god);

        }

    }

    private int calculateScore(JavaMetrics metrics) {

        int score = 0;

        score += metrics.getMethodCount() * 2;

        score += metrics.getDependencyCount() * 5;

        score += metrics.getFieldCount();

        if (metrics.getLineCount() > 500) {
            score += 20;
        }

        return score;

    }

}