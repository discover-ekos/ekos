package com.ekos.plugins.backend.java;

import com.ekos.discovery.model.ModuleInfo;
import com.ekos.discovery.model.ProjectStructure;

import java.nio.file.Path;

public class ModuleScanner {

    public void scan(Path projectRoot,
                     ProjectStructure structure) {

        ModuleInfo backend = new ModuleInfo();

        backend.setName("Backend");

        backend.setControllers(
                structure.getControllers().size());

        backend.setServices(
                structure.getServices().size());

        backend.setRepositories(
                structure.getRepositories().size());

        backend.setEntities(
                structure.getEntities().size());

        backend.setConfigurations(
                structure.getConfigurations().size());

        structure.getModules().add(backend);

    }

}