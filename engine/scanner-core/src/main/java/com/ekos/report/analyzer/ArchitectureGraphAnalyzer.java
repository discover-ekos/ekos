package com.ekos.report.analyzer;

import com.ekos.discovery.model.ComponentInfo;
import com.ekos.discovery.model.DiscoveryResult;
import com.ekos.discovery.model.ServiceCallInfo;
import com.ekos.report.analyzer.model.AnalysisResult;
import com.ekos.report.analyzer.model.GraphEdge;
import com.ekos.report.analyzer.model.GraphNode;

public class ArchitectureGraphAnalyzer {

    public void analyze(DiscoveryResult discovery,
                        AnalysisResult analysis) {

        addNodes(discovery, analysis);

        addEdges(discovery, analysis);

    }

    private void addNodes(DiscoveryResult discovery,
                          AnalysisResult analysis) {

        addComponentNodes(
                discovery.getStructure().getControllers(),
                "Controller",
                analysis);

        addComponentNodes(
                discovery.getStructure().getServices(),
                "Service",
                analysis);

        addComponentNodes(
                discovery.getStructure().getRepositories(),
                "Repository",
                analysis);

    }

    private void addComponentNodes(
            java.util.List<ComponentInfo> components,
            String type,
            AnalysisResult analysis) {

        for (ComponentInfo component : components) {

            GraphNode node = new GraphNode();

            node.setId(component.getName());

            node.setLabel(component.getName());

            node.setType(type);

            analysis.getGraphNodes().add(node);

        }

    }

    private void addEdges(DiscoveryResult discovery,
                          AnalysisResult analysis) {

        for (ServiceCallInfo call :
                discovery.getStructure().getServiceCalls()) {

            String controller = call.getController();

            if (controller == null) {
                continue;
            }

            // Controller -> Services
            for (String service : call.getServices()) {

                GraphEdge edge = new GraphEdge();

                edge.setFrom(controller);

                edge.setTo(service);

                edge.setRelation("calls");

                analysis.getGraphEdges().add(edge);

            }

            // Services -> Repositories
            for (String repository : call.getRepositories()) {

                for (String service : call.getServices()) {

                    GraphEdge edge = new GraphEdge();

                    edge.setFrom(service);

                    edge.setTo(repository);

                    edge.setRelation("uses");

                    analysis.getGraphEdges().add(edge);

                }

            }

        }

    }

}