package com.ekos.insights;

import com.ekos.discovery.model.ArchitectureIssue;
import com.ekos.discovery.model.DiscoveryResult;

public class MissingRepositoryAnalyzer {

    public void analyze(DiscoveryResult result) {

        if (result.getStructure().getRepositories().isEmpty()) {

            ArchitectureIssue issue = new ArchitectureIssue();

            issue.setSeverity("MEDIUM");
            issue.setCategory("Architecture");
            issue.setDescription("No Repository layer found.");

            result.getArchitectureIssues().add(issue);

        }

    }

}