package com.ekos.discovery.model;

public class PackageInfo {

    private String packageName;

    private int classCount;

    private int controllerCount;

    private int serviceCount;

    private int repositoryCount;

    private int entityCount;

    private int configurationCount;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getClassCount() {
        return classCount;
    }

    public void setClassCount(int classCount) {
        this.classCount = classCount;
    }

    public int getControllerCount() {
        return controllerCount;
    }

    public void setControllerCount(int controllerCount) {
        this.controllerCount = controllerCount;
    }

    public int getServiceCount() {
        return serviceCount;
    }

    public void setServiceCount(int serviceCount) {
        this.serviceCount = serviceCount;
    }

    public int getRepositoryCount() {
        return repositoryCount;
    }

    public void setRepositoryCount(int repositoryCount) {
        this.repositoryCount = repositoryCount;
    }

    public int getEntityCount() {
        return entityCount;
    }

    public void setEntityCount(int entityCount) {
        this.entityCount = entityCount;
    }

    public int getConfigurationCount() {
        return configurationCount;
    }

    public void setConfigurationCount(int configurationCount) {
        this.configurationCount = configurationCount;
    }
}