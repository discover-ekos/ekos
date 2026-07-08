package com.ekos.discovery.model;

public class HealthScore {

    private int overall;

    private int architecture;

    private int backend;

    private int database;

    private int dependencies;

    private int documentation;

    public int getOverall() {
        return overall;
    }

    public void setOverall(int overall) {
        this.overall = overall;
    }

    public int getArchitecture() {
        return architecture;
    }

    public void setArchitecture(int architecture) {
        this.architecture = architecture;
    }

    public int getBackend() {
        return backend;
    }

    public void setBackend(int backend) {
        this.backend = backend;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public int getDependencies() {
        return dependencies;
    }

    public void setDependencies(int dependencies) {
        this.dependencies = dependencies;
    }

    public int getDocumentation() {
        return documentation;
    }

    public void setDocumentation(int documentation) {
        this.documentation = documentation;
    }
}