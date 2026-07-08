package com.ekos.plugins.backend.java;

import com.ekos.discovery.model.ComponentInfo;
import com.ekos.discovery.model.ProjectStructure;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class JavaStructureScanner {

    public ProjectStructure scan(Path projectRoot) {

        ProjectStructure structure = new ProjectStructure();

        try (Stream<Path> files = Files.walk(projectRoot)) {

            files.filter(file -> file.toString().endsWith(".java"))
                    .forEach(file -> processJavaFile(file, structure));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return structure;
    }

    private void processJavaFile(Path file,
                                 ProjectStructure structure) {

        try {

            CompilationUnit cu = StaticJavaParser.parse(file);

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