package com.ekos.insights;

import com.ekos.discovery.model.DiscoveryResult;
import com.ekos.discovery.model.ProjectSummary;
import com.ekos.discovery.model.Technology;

public class ProjectSummaryGenerator {

    public void generate(DiscoveryResult result) {

        ProjectSummary summary = result.getSummary();

        summary.setProjectName(result.getProjectName());

        summary.setLanguage(result.getMetadata().getLanguage());

        summary.setFramework(result.getMetadata().getFramework());

        summary.setBuildTool(result.getMetadata().getBuildTool());

        summary.setJavaVersion(result.getMetadata().getJavaVersion());

        summary.setControllerCount(
                result.getStructure().getControllers().size());

        summary.setServiceCount(
                result.getStructure().getServices().size());

        summary.setRepositoryCount(
                result.getStructure().getRepositories().size());

        summary.setEntityCount(
                result.getStructure().getDatabase().getTables().size());

        summary.setTableCount(
                result.getStructure().getDatabase().getTables().size());

        summary.setDatabase(
                result.getStructure().getDatabase().getDatabaseType());

        summary.setRestApiCount(
                result.getStructure().getRestEndpoints().size());

        summary.setOverallHealth(
                result.getHealthScore().getOverall());

        for (Technology technology : result.getTechnologies()) {
            summary.getTechnologies().add(technology.getName());
        }
    }
}