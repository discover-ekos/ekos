package com.ekos.plugins.backend.java.pipeline;

import com.ekos.discovery.model.DiscoveryResult;

import java.nio.file.Path;

public interface DiscoveryStep {

    void execute(Path projectRoot,
                 DiscoveryResult result);

}