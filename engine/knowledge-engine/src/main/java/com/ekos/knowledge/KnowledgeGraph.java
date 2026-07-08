package com.ekos.knowledge;

import java.util.ArrayList;
import java.util.List;

public class KnowledgeGraph {

    private List<KnowledgeNode> nodes = new ArrayList<>();

    private List<KnowledgeEdge> edges = new ArrayList<>();

    public List<KnowledgeNode> getNodes() {
        return nodes;
    }

    public List<KnowledgeEdge> getEdges() {
        return edges;
    }

}