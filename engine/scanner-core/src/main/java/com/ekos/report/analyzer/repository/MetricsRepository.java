package com.ekos.report.analyzer.repository;

import com.ekos.report.analyzer.model.JavaMetrics;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MetricsRepository {

    private final Map<String, JavaMetrics> metrics =
            new HashMap<>();

    public void add(JavaMetrics metric) {

        metrics.put(metric.getClassName(), metric);

    }

    public JavaMetrics get(String className) {

        return metrics.get(className);

    }

    public Collection<JavaMetrics> getAll() {

        return metrics.values();

    }

}