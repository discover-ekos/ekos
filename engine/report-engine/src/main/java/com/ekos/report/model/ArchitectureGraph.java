package com.ekos.report.model;

import java.util.ArrayList;
import java.util.List;

public class ArchitectureGraph {

    private List<ArchitectureNode> nodes = new ArrayList<>();

    private List<ArchitectureEdge> edges = new ArrayList<>();

    public List<ArchitectureNode> getNodes() {
        return nodes;
    }

    public List<ArchitectureEdge> getEdges() {
        return edges;
    }
}