package com.ekos.scanner;

import com.ekos.discovery.model.DiscoveryResult;
import com.ekos.plugin.DiscoveryPlugin;

import java.nio.file.Path;
import java.util.List;

public class ScannerEngine {

    private final PluginRegistry pluginRegistry;


    public ScannerEngine(PluginRegistry pluginRegistry) {
        this.pluginRegistry = pluginRegistry;
    }

    public DiscoveryResult scan(Path projectRoot) {

        DiscoveryResult finalResult = new DiscoveryResult();

        for (DiscoveryPlugin plugin : pluginRegistry.getPlugins()) {

            if (plugin.supports(projectRoot)) {

                DiscoveryResult result = plugin.scan(projectRoot);

                finalResult.getTechnologies()
                        .addAll(result.getTechnologies());

                if (result.getMetadata() != null) {
                    finalResult.setMetadata(result.getMetadata());
                }

                if (result.getProjectName() != null) {
                    finalResult.setProjectName(result.getProjectName());
                }
            }
        }

        return finalResult;
    }

}