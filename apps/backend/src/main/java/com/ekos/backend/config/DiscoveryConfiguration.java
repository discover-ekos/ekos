package com.ekos.backend.config;

import com.ekos.insights.ArchitectureAnalyzer;
import com.ekos.insights.HealthScoreCalculator;
import com.ekos.insights.ProjectSummaryGenerator;
import com.ekos.plugins.backend.java.JavaDiscoveryPlugin;
import com.ekos.scanner.PluginRegistry;
import com.ekos.scanner.ScannerEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscoveryConfiguration {

    @Bean
    public PluginRegistry pluginRegistry() {

        PluginRegistry registry = new PluginRegistry();

        registry.register(new JavaDiscoveryPlugin());

        return registry;
    }

    @Bean
    public ScannerEngine scannerEngine(PluginRegistry registry) {

        return new ScannerEngine(registry);

    }

    @Bean
    public ArchitectureAnalyzer architectureAnalyzer() {
        return new ArchitectureAnalyzer();
    }

    @Bean
    public HealthScoreCalculator healthScoreCalculator() {
        return new HealthScoreCalculator();
    }

    @Bean
    public ProjectSummaryGenerator projectSummaryGenerator() {
        return new ProjectSummaryGenerator();
    }
}