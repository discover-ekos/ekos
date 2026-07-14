package com.ekos.plugins.backend.java.discovery;

import com.ekos.discovery.model.ComponentInfo;
import com.ekos.discovery.model.ProjectStructure;
import com.ekos.plugins.backend.java.scanner.AbstractJavaScanner;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import java.nio.file.Path;

public class StructureDiscoveryStep extends AbstractJavaScanner {

    public ProjectStructure scan(Path projectRoot) {

        ProjectStructure structure = new ProjectStructure();

        scanJavaFiles(projectRoot,
                (cu, file) -> processCompilationUnit(cu, file, structure));
        return structure;
    }

    private void processCompilationUnit(
            CompilationUnit cu,
            Path file, ProjectStructure structure) {

        try {

            cu.findAll(ClassOrInterfaceDeclaration.class)
                    .forEach(clazz -> {

                        String packageName =
                                cu.getPackageDeclaration()
                                        .map(p -> p.getNameAsString())
                                        .orElse("");

                        ComponentInfo info =
                                new ComponentInfo(
                                        clazz.getNameAsString(),
                                        packageName,
                                        "",
                                        file.toString()
                                );

                        clazz.getAnnotations().forEach(annotation -> {

                            switch (annotation.getNameAsString()) {

                                case "RestController":
                                case "Controller":
                                    info.setType("Controller");
                                    structure.getControllers().add(info);
                                    break;

                                case "Service":
                                    info.setType("Service");
                                    structure.getServices().add(info);
                                    break;

                                case "Repository":
                                    info.setType("Repository");
                                    structure.getRepositories().add(info);
                                    break;

                                case "Entity":
                                    info.setType("Entity");
                                    structure.getEntities().add(info);
                                    break;

                                case "Configuration":
                                    info.setType("Configuration");
                                    structure.getConfigurations().add(info);
                                    break;

                                case "Component":
                                    info.setType("Component");
                                    structure.getComponents().add(info);
                                    break;
                            }

                        });

                    });

        } catch (Exception ignored) {
        }

    }

}