package com.ekos.report.graph;

import com.ekos.discovery.model.ComponentInfo;
import com.ekos.discovery.model.DiscoveryResult;
import com.ekos.discovery.model.ServiceCallInfo;

public class ArchitectureGraphBuilder {

    public ArchitectureGraph build(DiscoveryResult result) {

        ArchitectureGraph graph = new ArchitectureGraph();

        addNodes(graph, result);

        addEdges(graph, result);

        return graph;
    }

    private void addNodes(ArchitectureGraph graph,
                          DiscoveryResult result) {

        addNodes(graph,
                result.getStructure().getControllers(),
                "Controller");

        addNodes(graph,
                result.getStructure().getServices(),
                "Service");

        addNodes(graph,
                result.getStructure().getRepositories(),
                "Repository");

        addNodes(graph,
                result.getStructure().getEntities(),
                "Entity");

    }

    private void addNodes(ArchitectureGraph graph,
                          java.util.List<ComponentInfo> components,
                          String type) {

        for (ComponentInfo component : components) {

            ArchitectureNode node = new ArchitectureNode();

            node.setId(component.getName());

            node.setName(component.getName());

            node.setType(type);

            graph.getNodes().add(node);

        }

    }

    private void addEdges(ArchitectureGraph graph,
                          DiscoveryResult result) {

        for (ServiceCallInfo call :
                result.getStructure().getServiceCalls()) {

            // Controller -> Service

            for (String service : call.getServices()) {

                ArchitectureEdge edge = new ArchitectureEdge();

                edge.setSource(call.getController());

                edge.setTarget(service);

                edge.setRelation("CALLS");

                graph.getEdges().add(edge);

            }

            // Service -> Repository

            String primaryService = call.getPrimaryService();

            if (primaryService != null) {

                for (String repository : call.getRepositories()) {

                    ArchitectureEdge edge = new ArchitectureEdge();

                    edge.setSource(primaryService);

                    edge.setTarget(repository);

                    edge.setRelation("USES");

                    graph.getEdges().add(edge);

                }

            }

        }

    }

}