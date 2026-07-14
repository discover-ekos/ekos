package com.ekos.report.analyzer;

import com.ekos.discovery.model.ArchitectureIssue;
import com.ekos.discovery.model.DiscoveryResult;

public class LayerViolationAnalyzer {

    public void analyze(DiscoveryResult result) {
        result.getStructure()
                .getServiceCalls()
                .forEach(call -> {

                    if (call.getController() != null &&
                            !call.getRepositories().isEmpty()) {

                        ArchitectureIssue issue = new ArchitectureIssue();

                        issue.setType("Layer Violation");

                        issue.setCategory("Architecture");

                        issue.setSeverity("HIGH");

                        issue.setComponent(call.getController());

                        issue.setDescription(
                                call.getController()
                                        + " directly accesses repository.");

                        issue.setRecommendation(
                                "Move repository access into a Service layer.");

                        result.getArchitectureIssues()
                                .add(issue);

                    }

                });
    }

}