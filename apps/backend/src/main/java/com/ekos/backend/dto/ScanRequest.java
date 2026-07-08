package com.ekos.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class ScanRequest {

    @NotBlank
    private String projectPath;

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }
}