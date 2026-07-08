package com.ekos.discovery.model;

import java.util.ArrayList;
import java.util.List;

public class ProjectStructure {

    private List<ComponentInfo> controllers = new ArrayList<>();

    private List<ComponentInfo> services = new ArrayList<>();

    private List<ComponentInfo> repositories = new ArrayList<>();

    private List<ComponentInfo> entities = new ArrayList<>();

    private List<ComponentInfo> configurations = new ArrayList<>();

    private List<ComponentInfo> components = new ArrayList<>();

    private List<RestEndpoint> restEndpoints = new ArrayList<>();

    private List<DependencyRelation> dependencies = new ArrayList<>();

    private DatabaseInfo database = new DatabaseInfo();

    private List<ComponentRelation> componentRelations = new ArrayList<>();

    public List<ComponentInfo> getControllers() {
        return controllers;
    }

    public void setControllers(List<ComponentInfo> controllers) {
        this.controllers = controllers;
    }

    public List<ComponentInfo> getServices() {
        return services;
    }

    public void setServices(List<ComponentInfo> services) {
        this.services = services;
    }

    public List<ComponentInfo> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<ComponentInfo> repositories) {
        this.repositories = repositories;
    }

    public List<ComponentInfo> getEntities() {
        return entities;
    }

    public void setEntities(List<ComponentInfo> entities) {
        this.entities = entities;
    }

    public List<ComponentInfo> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(List<ComponentInfo> configurations) {
        this.configurations = configurations;
    }

    public List<ComponentInfo> getComponents() {
        return components;
    }

    public void setComponents(List<ComponentInfo> components) {
        this.components = components;
    }

    public List<RestEndpoint> getRestEndpoints() {
        return restEndpoints;
    }

    public void setRestEndpoints(List<RestEndpoint> restEndpoints) {
        this.restEndpoints = restEndpoints;
    }

    public List<DependencyRelation> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<DependencyRelation> dependencies) {
        this.dependencies = dependencies;
    }

    public DatabaseInfo getDatabase() {
        return database;
    }

    public void setDatabase(DatabaseInfo database) {
        this.database = database;
    }

    public List<ComponentRelation> getComponentRelations() {
        return componentRelations;
    }

    public void setComponentRelations(List<ComponentRelation> componentRelations) {
        this.componentRelations = componentRelations;
    }

    public int getControllerCount() {
        return controllers.size();
    }

    public int getServiceCount() {
        return services.size();
    }

    public int getRepositoryCount() {
        return repositories.size();
    }

    public int getEntityCount() {
        return entities.size();
    }

    public int getRestEndpointCount() {
        return restEndpoints.size();
    }
}