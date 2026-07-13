package com.ekos.insights;

import com.ekos.discovery.model.ApiInventory;
import com.ekos.discovery.model.ArchitectureIssue;
import com.ekos.discovery.model.DiscoveryResult;

public class MissingServiceAnalyzer {

    public void analyze(DiscoveryResult result) {

        for (ApiInventory api : result.getStructure().getApiInventory()) {

            if ("Unknown".equals(api.getService())) {

                ArchitectureIssue issue = new ArchitectureIssue();

                issue.setSeverity("MEDIUM");
                issue.setCategory("Architecture");
                issue.setDescription(
                        api.getController()
                                + " has no mapped Service."
                );

                result.getArchitectureIssues().add(issue);

            }

        }

    }

}