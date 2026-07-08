package com.ekos.plugins.backend.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class DatasourceDetector {

    public String detect(Path projectRoot) {

        try (Stream<Path> files = Files.walk(projectRoot)) {

            Path properties = files
                    .filter(this::isConfigurationFile)
                    .findFirst()
                    .orElse(null);

            if (properties == null) {
                return "Unknown";
            }

            String content = Files.readString(properties).toLowerCase();

            if (content.contains("postgresql")) {
                return "PostgreSQL";
            }

            if (content.contains("oracle")) {
                return "Oracle";
            }

            if (content.contains("mysql")) {
                return "MySQL";
            }

            if (content.contains("sqlserver")) {
                return "SQL Server";
            }

            if (content.contains("mongodb")) {
                return "MongoDB";
            }

            return "Unknown";

        } catch (IOException e) {
            return "Unknown";
        }

    }

    private boolean isConfigurationFile(Path file) {

        String name = file.getFileName().toString();

        return name.equals("application.properties")
                || name.equals("application.yml")
                || name.equals("application.yaml");
    }

}