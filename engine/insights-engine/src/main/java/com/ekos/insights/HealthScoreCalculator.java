package com.ekos.insights;

import com.ekos.discovery.model.DiscoveryResult;
import com.ekos.discovery.model.HealthScore;

public class HealthScoreCalculator {

    public void calculate(DiscoveryResult result) {

        int architecture = 100;
        int backend = 100;
        int database = 100;
        int dependencies = 100;
        int documentation = 100;

        // Architecture

        if (result.getStructure().getControllers().isEmpty())
            architecture -= 20;

        if (result.getStructure().getServices().isEmpty())
            architecture -= 20;

        if (result.getStructure().getRepositories().isEmpty())
            architecture -= 20;

        // Backend

        if (result.getStructure().getRestEndpoints().isEmpty())
            backend -= 20;

        // Database

        if (result.getStructure().getDatabase().getTables().isEmpty())
            database -= 20;

        // Technologies

        if (result.getTechnologies().isEmpty())
            dependencies -= 20;

        // Documentation (temporary)

        if (result.getMetadata().getArtifactId() == null)
            documentation -= 20;

        int overall =
                (architecture +
                        backend +
                        database +
                        dependencies +
                        documentation) / 5;

        HealthScore score = result.getHealthScore();

        score.setArchitecture(architecture);
        score.setBackend(backend);
        score.setDatabase(database);
        score.setDependencies(dependencies);
        score.setDocumentation(documentation);
        score.setOverall(overall);

    }

}