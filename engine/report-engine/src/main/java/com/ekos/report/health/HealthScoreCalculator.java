package com.ekos.report.health;

import com.ekos.discovery.model.ArchitectureIssue;
import com.ekos.discovery.model.DiscoveryResult;
import com.ekos.discovery.model.HealthScore;

public class HealthScoreCalculator {

    public void calculate(DiscoveryResult result) {

        int score = 100;

        for (ArchitectureIssue issue :
                result.getArchitectureIssues()) {

            switch (issue.getSeverity()) {

                case "HIGH" -> score -= 10;

                case "MEDIUM" -> score -= 5;

                case "LOW" -> score -= 2;

            }

        }

        if (score < 0) {
            score = 0;
        }

        HealthScore health = result.getHealthScore();

        health.setOverall(score);

        health.setArchitecture(score);

        health.setCodeQuality(score);

        health.setMaintainability(score);

    }

}