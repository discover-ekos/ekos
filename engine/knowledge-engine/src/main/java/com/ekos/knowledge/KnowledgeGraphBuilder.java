package com.ekos.knowledge;

import com.ekos.discovery.model.ComponentInfo;
import com.ekos.discovery.model.DependencyRelation;
import com.ekos.discovery.model.DiscoveryResult;

public class KnowledgeGraphBuilder {

    public KnowledgeGraph build(DiscoveryResult discoveryResult) {

        KnowledgeGraph graph = new KnowledgeGraph();

        addControllers(graph, discoveryResult);

        addServices(graph, discoveryResult);

        addRepositories(graph, discoveryResult);

        addEntities(graph, discoveryResult);

        addDependencies(graph, discoveryResult);

        return graph;
    }

    private void addControllers(KnowledgeGraph graph,
                                DiscoveryResult result) {

        for (ComponentInfo controller : result.getStructure().getControllers()) {

            graph.getNodes().add(
                    new KnowledgeNode(
                            controller.getName(),
                            "CONTROLLER",
                            controller.getName()
                    )
            );

        }

    }

    private void addServices(KnowledgeGraph graph,
                             DiscoveryResult result) {

        for (ComponentInfo service : result.getStructure().getServices()) {

            graph.getNodes().add(
                    new KnowledgeNode(
                            service.getName(),
                            "SERVICE",
                            service.getName()
                    )
            );

        }

    }

    private void addRepositories(KnowledgeGraph graph,
                                 DiscoveryResult result) {

        for (ComponentInfo repository : result.getStructure().getRepositories()) {

            graph.getNodes().add(
                    new KnowledgeNode(
                            repository.getName(),
                            "REPOSITORY",
                            repository.getName()
                    )
            );

        }

    }

    private void addEntities(KnowledgeGraph graph,
                             DiscoveryResult result) {

        result.getStructure()
                .getDatabase()
                .getTables()
                .forEach(table -> {

                    graph.getNodes().add(
                            new KnowledgeNode(
                                    table.getEntityName(),
                                    "ENTITY",
                                    table.getEntityName()
                            )
                    );

                });

    }

    private void addDependencies(KnowledgeGraph graph,
                                 DiscoveryResult result) {

        for (DependencyRelation relation :
                result.getStructure().getDependencies()) {

            graph.getEdges().add(
                    new KnowledgeEdge(
                            relation.getSource(),
                            relation.getTarget(),
                            relation.getType()
                    )
            );

        }

    }

}