package com.ekos.discovery.model;

import java.util.ArrayList;
import java.util.List;

public class DatabaseInfo {

    private String databaseType;

    private List<TableInfo> tables = new ArrayList<>();

    private List<RepositoryInfo> repositories = new ArrayList<>();

    public String getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }

    public List<TableInfo> getTables() {
        return tables;
    }

    public List<RepositoryInfo> getRepositories() {
        return repositories;
    }
}