package com.ekos.backend.dto;

import java.util.List;

public class ScanResponse {

    private String projectName;

    private List<String> technologies;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<String> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<String> technologies) {
        this.technologies = technologies;
    }
}