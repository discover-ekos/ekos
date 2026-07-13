package com.ekos.insights;

import com.ekos.discovery.model.ApiInventory;
import com.ekos.discovery.model.ArchitectureIssue;
import com.ekos.discovery.model.DiscoveryResult;

import java.util.HashSet;
import java.util.Set;

public class DuplicateApiAnalyzer {

    public void analyze(DiscoveryResult result) {

        Set<String> discovered = new HashSet<>();

        for (ApiInventory api : result.getStructure().getApiInventory()) {

            String key = api.getMethod() + ":" + api.getPath();

            if (!discovered.add(key)) {

                ArchitectureIssue issue = new ArchitectureIssue();

                issue.setSeverity("HIGH");
                issue.setCategory("API");
                issue.setDescription("Duplicate API found : " + key);

                result.getArchitectureIssues().add(issue);
            }

        }

    }

}