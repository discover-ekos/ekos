package com.ekos.discovery.model;

public class DependencyHeat {

    private String component;

    private int outgoingDependencies;

    private int incomingDependencies;

    private int totalDependencies;

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public int getOutgoingDependencies() {
        return outgoingDependencies;
    }

    public void setOutgoingDependencies(int outgoingDependencies) {
        this.outgoingDependencies = outgoingDependencies;
    }

    public int getIncomingDependencies() {
        return incomingDependencies;
    }

    public void setIncomingDependencies(int incomingDependencies) {
        this.incomingDependencies = incomingDependencies;
    }

    public int getTotalDependencies() {
        return totalDependencies;
    }

    public void setTotalDependencies(int totalDependencies) {
        this.totalDependencies = totalDependencies;
    }
}