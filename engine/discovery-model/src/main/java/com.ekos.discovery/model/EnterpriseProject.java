package com.ekos.discovery.model;

import java.util.ArrayList;
import java.util.List;

public class EnterpriseProject {

    private String id;

    private String name;

    private String path;

    private List<Technology> technologies = new ArrayList<>();

    public EnterpriseProject() {
    }

    public EnterpriseProject(String id, String name, String path) {
        this.id = id;
        this.name = name;
        this.path = path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Technology> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<Technology> technologies) {
        this.technologies = technologies;
    }
}