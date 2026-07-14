package com.ekos.report.analyzer.model;

public class DependencyHeat {

    private String component;

    private int incoming;

    private int outgoing;

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public int getIncoming() {
        return incoming;
    }

    public void setIncoming(int incoming) {
        this.incoming = incoming;
    }

    public int getOutgoing() {
        return outgoing;
    }

    public void setOutgoing(int outgoing) {
        this.outgoing = outgoing;
    }

    public int getTotal() {
        return incoming + outgoing;
    }
}