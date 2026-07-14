package com.ekos.report.analyzer.model;

import com.ekos.report.analyzer.repository.MetricsRepository;

import java.util.ArrayList;
import java.util.List;

public class AnalysisResult {

    private List<DependencyHeat> dependencyHeatmap = new ArrayList<>();

    private List<GodClass> godClasses = new ArrayList<>();

    private List<PackageHealth> packageHealth = new ArrayList<>();

    private AiReadiness aiReadiness = new AiReadiness();

    private MetricsRepository metricsRepository;

    private List<DeadComponent> deadComponents = new ArrayList<>();

    private List<GraphNode> graphNodes = new ArrayList<>();

    private List<GraphEdge> graphEdges = new ArrayList<>();

    public List<DependencyHeat> getDependencyHeatmap() {
        return dependencyHeatmap;
    }

    public List<GodClass> getGodClasses() {
        return godClasses;
    }

    public List<PackageHealth> getPackageHealth() {
        return packageHealth;
    }

    public AiReadiness getAiReadiness() {
        return aiReadiness;
    }

    public MetricsRepository getMetricsRepository() {
        return metricsRepository;
    }

    public void setMetricsRepository(MetricsRepository metricsRepository) {
        this.metricsRepository = metricsRepository;
    }

    public List<DeadComponent> getDeadComponents() {
        return deadComponents;
    }

    public void setDeadComponents(List<DeadComponent> deadComponents) {
        this.deadComponents = deadComponents;
    }

    public List<GraphNode> getGraphNodes() {
        return graphNodes;
    }

    public void setGraphNodes(List<GraphNode> graphNodes) {
        this.graphNodes = graphNodes;
    }

    public List<GraphEdge> getGraphEdges() {
        return graphEdges;
    }

    public void setGraphEdges(List<GraphEdge> graphEdges) {
        this.graphEdges = graphEdges;
    }
}