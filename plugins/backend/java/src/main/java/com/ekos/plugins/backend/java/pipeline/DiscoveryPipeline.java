package com.ekos.plugins.backend.java.pipeline;

import com.ekos.discovery.model.DiscoveryResult;

import java.nio.file.Path;
import java.util.List;

public class DiscoveryPipeline {

    private final List<DiscoveryStep> steps;

    public DiscoveryPipeline(List<DiscoveryStep> steps) {
        this.steps = steps;
    }

    public void execute(Path root,
                        DiscoveryResult result) {

        for (DiscoveryStep step : steps) {

            step.execute(root, result);

        }

    }

}