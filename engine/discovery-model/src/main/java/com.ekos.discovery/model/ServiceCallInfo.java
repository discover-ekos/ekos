package com.ekos.discovery.model;

import java.util.ArrayList;
import java.util.List;

public class ServiceCallInfo {

    private String controller;

    private List<String> services = new ArrayList<>();

    private List<String> repositories = new ArrayList<>();

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }

    public List<String> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<String> repositories) {
        this.repositories = repositories;
    }

    // Convenience methods
    public void addService(String service) {
        if (!services.contains(service)) {
            services.add(service);
        }
    }

    public void addRepository(String repository) {
        if (!repositories.contains(repository)) {
            repositories.add(repository);
        }
    }

    public String getPrimaryService() {
        return services.isEmpty() ? null : services.get(0);
    }

    public String getPrimaryRepository() {
        return repositories.isEmpty() ? null : repositories.get(0);
    }
}