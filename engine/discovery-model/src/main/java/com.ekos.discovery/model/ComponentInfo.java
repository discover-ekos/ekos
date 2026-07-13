package com.ekos.discovery.model;

public class ComponentInfo {

    private String name;

    private String packageName;

    private String type;

    private String filePath;

    public ComponentInfo(String name, String packageName, String type, String filePath) {
        this.name = name;
        this.packageName = packageName;
        this.type = type;
        this.filePath = filePath;
    }

    public ComponentInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}