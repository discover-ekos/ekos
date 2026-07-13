package com.ekos.discovery.model;

public class ModuleInfo {

    private String name;

    private int controllers;

    private int services;

    private int repositories;

    private int entities;

    private int configurations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getControllers() {
        return controllers;
    }

    public void setControllers(int controllers) {
        this.controllers = controllers;
    }

    public int getServices() {
        return services;
    }

    public void setServices(int services) {
        this.services = services;
    }

    public int getRepositories() {
        return repositories;
    }

    public void setRepositories(int repositories) {
        this.repositories = repositories;
    }

    public int getEntities() {
        return entities;
    }

    public void setEntities(int entities) {
        this.entities = entities;
    }

    public int getConfigurations() {
        return configurations;
    }

    public void setConfigurations(int configurations) {
        this.configurations = configurations;
    }
}