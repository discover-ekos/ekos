package com.ekos.report.analyzer;

import com.ekos.discovery.model.*;

import java.util.HashMap;
import java.util.Map;

public class DependencyHeatmapAnalyzer {

    public void analyze(DiscoveryResult result) {

        Map<String, DependencyHeat> heatMap = new HashMap<>();

        for (DependencyRelation relation :
                result.getStructure().getDependencies()) {

            DependencyHeat source = heatMap.computeIfAbsent(
                    relation.getSource(),
                    this::create);

            source.setOutgoingDependencies(
                    source.getOutgoingDependencies() + 1);

            DependencyHeat target = heatMap.computeIfAbsent(
                    relation.getTarget(),
                    this::create);

            target.setIncomingDependencies(
                    target.getIncomingDependencies() + 1);
        }

        for (DependencyHeat heat : heatMap.values()) {

            heat.setTotalDependencies(
                    heat.getIncomingDependencies()
                            + heat.getOutgoingDependencies());

            result.getStructure()
                    .getDependencyHeatmap()
                    .add(heat);
        }
    }

    private DependencyHeat create(String component) {

        DependencyHeat heat = new DependencyHeat();

        heat.setComponent(component);

        return heat;
    }
}