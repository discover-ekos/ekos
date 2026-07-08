package com.ekos.plugin;

import com.ekos.discovery.model.DiscoveryResult;

import java.nio.file.Path;

public interface DiscoveryPlugin {

    /**
     * Plugin name
     */
    String getName();

    /**
     * Is this plugin applicable to the given project?
     */
    boolean supports(Path projectRoot);

    /**
     * Execute discovery.
     */
    DiscoveryResult scan(Path projectRoot);

}