package com.ekos.plugins.backend.java;

import com.ekos.discovery.model.DiscoveryResult;
import com.ekos.discovery.model.ProjectMetadata;
import com.ekos.discovery.model.Technology;
import com.ekos.plugin.DiscoveryPlugin;

import java.nio.file.Files;
import java.nio.file.Path;

public class JavaDiscoveryPlugin implements DiscoveryPlugin {

    private final JavaStructureScanner structureScanner =
            new JavaStructureScanner();

    private final DependencyScanner dependencyScanner =
            new DependencyScanner();

    private final DatabaseScanner databaseScanner =
            new DatabaseScanner();

    private final PomParser pomParser = new PomParser();

    private final RestApiScanner restApiScanner =
            new RestApiScanner();

    @Override
    public String getName() {
        return "Java Discovery Plugin";
    }

    @Override
    public boolean supports(Path projectRoot) {
        return Files.exists(projectRoot.resolve("pom.xml"));
    }

    @Override
    public DiscoveryResult scan(Path projectRoot) {

        DiscoveryResult result = new DiscoveryResult();

        if (!supports(projectRoot)) {
            return result;
        }

        ProjectMetadata metadata =
                pomParser.parse(projectRoot.resolve("pom.xml").toFile());

        result.setProjectName(metadata.getArtifactId());
        result.setMetadata(metadata);
        result.setStructure(
                structureScanner.scan(projectRoot)
        );
        restApiScanner.scan(
                projectRoot,
                result.getStructure()
        );

        dependencyScanner.scan(
                projectRoot,
                result.getStructure()
        );

        databaseScanner.scan(
                projectRoot,
                result.getStructure()
        );

        result.getTechnologies().add(new Technology("Java", metadata.getJavaVersion()));
        result.getTechnologies().add(new Technology("Maven", metadata.getBuildTool()));

        if (metadata.getFramework() != null) {
            result.getTechnologies().add(
                    new Technology(
                            metadata.getFramework(),
                            metadata.getFrameworkVersion()
                    )
            );
        }

        return result;
    }
}