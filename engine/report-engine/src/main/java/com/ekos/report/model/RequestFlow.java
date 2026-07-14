package com.ekos.report.model;

import java.util.ArrayList;
import java.util.List;

public class RequestFlow {

    private String api;

    private List<String> steps = new ArrayList<>();

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void addStep(String step) {
        steps.add(step);
    }

}