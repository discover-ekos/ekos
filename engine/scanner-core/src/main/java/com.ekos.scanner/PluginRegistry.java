package com.ekos.scanner;

import com.ekos.plugin.DiscoveryPlugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PluginRegistry {

    private final List<DiscoveryPlugin> plugins = new ArrayList<>();

    public void register(DiscoveryPlugin plugin) {
        plugins.add(plugin);
    }

    public List<DiscoveryPlugin> getPlugins() {
        return Collections.unmodifiableList(plugins);
    }
}