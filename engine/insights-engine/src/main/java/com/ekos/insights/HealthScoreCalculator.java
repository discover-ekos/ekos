package com.ekos.insights;

import com.ekos.discovery.model.DiscoveryResult;
import com.ekos.discovery.model.HealthScore;

public class HealthScoreCalculator {

    public void calculate(DiscoveryResult result) {

        HealthScore score = result.getHealthScore();

        score.setArchitecture(100);
        score.setBackend(100);
        score.setDatabase(100);
        score.setDependencies(100);
        score.setDocumentation(100);

        score.setOverall(100);

    }

}