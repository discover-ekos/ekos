package com.ekos.discovery.model;

import java.util.ArrayList;
import java.util.List;

public class DiscoveryResult {

    private String projectName;

    private ProjectMetadata metadata = new ProjectMetadata();

    private ProjectStructure structure = new ProjectStructure();

    private List<Technology> technologies = new ArrayList<>();

    private List<ArchitectureIssue> architectureIssues = new ArrayList<>();

    private HealthScore healthScore = new HealthScore();

    private ProjectSummary summary = new ProjectSummary();

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public ProjectMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(ProjectMetadata metadata) {
        this.metadata = metadata;
    }

    public List<Technology> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<Technology> technologies) {
        this.technologies = technologies;
    }

    public ProjectStructure getStructure() {
        return structure;
    }

    public void setStructure(ProjectStructure structure) {
        this.structure = structure;
    }

    public List<ArchitectureIssue> getArchitectureIssues() {
        return architectureIssues;
    }

    public void setArchitectureIssues(List<ArchitectureIssue> architectureIssues) {
        this.architectureIssues = architectureIssues;
    }

    public HealthScore getHealthScore() {
        return healthScore;
    }

    public void setHealthScore(HealthScore healthScore) {
        this.healthScore = healthScore;
    }

    public ProjectSummary getSummary() {
        return summary;
    }

    public void setSummary(ProjectSummary summary) {
        this.summary = summary;
    }
}