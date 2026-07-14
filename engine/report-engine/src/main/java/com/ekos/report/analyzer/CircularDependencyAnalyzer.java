package com.ekos.report.analyzer;

import com.ekos.discovery.model.ArchitectureIssue;
import com.ekos.discovery.model.ComponentRelation;
import com.ekos.discovery.model.DiscoveryResult;

public class CircularDependencyAnalyzer {

    public void analyze(DiscoveryResult result) {

        for (ComponentRelation first :
                result.getStructure().getComponentRelations()) {

            for (ComponentRelation second :
                    result.getStructure().getComponentRelations()) {

                if (first == second) {
                    continue;
                }

                if (first.getSource().equals(second.getTarget())
                        && first.getTarget().equals(second.getSource())) {

                    ArchitectureIssue issue =
                            new ArchitectureIssue();

                    issue.setType("Circular Dependency");

                    issue.setCategory("Architecture");

                    issue.setSeverity("HIGH");

                    issue.setComponent(
                            first.getSource());

                    issue.setDescription(

                            first.getSource()
                                    + " <-> "
                                    + first.getTarget());

                    issue.setRecommendation(

                            "Break the dependency using interface or event-driven communication.");

                    result.getArchitectureIssues().add(issue);

                }

            }

        }

    }

}