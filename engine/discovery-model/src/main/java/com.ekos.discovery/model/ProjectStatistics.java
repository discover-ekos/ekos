package com.ekos.discovery.model;

public class ProjectStatistics {

    private int totalControllers;
    private int totalServices;
    private int totalRepositories;
    private int totalApis;
    private int totalTechnologies;

    public int getTotalControllers() {
        return totalControllers;
    }

    public void setTotalControllers(int totalControllers) {
        this.totalControllers = totalControllers;
    }

    public int getTotalServices() {
        return totalServices;
    }

    public void setTotalServices(int totalServices) {
        this.totalServices = totalServices;
    }

    public int getTotalRepositories() {
        return totalRepositories;
    }

    public void setTotalRepositories(int totalRepositories) {
        this.totalRepositories = totalRepositories;
    }

    public int getTotalApis() {
        return totalApis;
    }

    public void setTotalApis(int totalApis) {
        this.totalApis = totalApis;
    }

    public int getTotalTechnologies() {
        return totalTechnologies;
    }

    public void setTotalTechnologies(int totalTechnologies) {
        this.totalTechnologies = totalTechnologies;
    }
}