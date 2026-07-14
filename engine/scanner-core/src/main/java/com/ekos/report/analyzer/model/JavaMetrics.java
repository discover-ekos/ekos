package com.ekos.report.analyzer.model;

public class JavaMetrics {

    private String className;

    private int methodCount;

    private int fieldCount;

    private int constructorCount;

    private int publicMethodCount;

    private int privateMethodCount;

    private int dependencyCount;

    private int lineCount;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getMethodCount() {
        return methodCount;
    }

    public void setMethodCount(int methodCount) {
        this.methodCount = methodCount;
    }

    public int getFieldCount() {
        return fieldCount;
    }

    public void setFieldCount(int fieldCount) {
        this.fieldCount = fieldCount;
    }

    public int getConstructorCount() {
        return constructorCount;
    }

    public void setConstructorCount(int constructorCount) {
        this.constructorCount = constructorCount;
    }

    public int getPublicMethodCount() {
        return publicMethodCount;
    }

    public void setPublicMethodCount(int publicMethodCount) {
        this.publicMethodCount = publicMethodCount;
    }

    public int getPrivateMethodCount() {
        return privateMethodCount;
    }

    public void setPrivateMethodCount(int privateMethodCount) {
        this.privateMethodCount = privateMethodCount;
    }

    public int getDependencyCount() {
        return dependencyCount;
    }

    public void setDependencyCount(int dependencyCount) {
        this.dependencyCount = dependencyCount;
    }

    public int getLineCount() {
        return lineCount;
    }

    public void setLineCount(int lineCount) {
        this.lineCount = lineCount;
    }
}