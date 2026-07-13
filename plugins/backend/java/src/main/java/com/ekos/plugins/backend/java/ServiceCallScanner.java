package com.ekos.plugins.backend.java;

import com.ekos.discovery.model.ComponentInfo;
import com.ekos.discovery.model.ProjectStructure;
import com.ekos.discovery.model.ServiceCallInfo;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class ServiceCallScanner extends AbstractJavaScanner {

    public void scan(Path projectRoot,
                     ProjectStructure structure) {

        scanJavaFiles(projectRoot,
                (cu, file) -> processCompilationUnit(cu, structure));

    }

    private void processCompilationUnit(
            CompilationUnit cu,
            ProjectStructure structure) {

        try {

            cu.findAll(ClassOrInterfaceDeclaration.class)
                    .forEach(clazz -> {

                        ServiceCallInfo info = new ServiceCallInfo();

                        String className = clazz.getNameAsString();

                        if (contains(structure.getControllers(), className)) {

                            info.setController(className);

                            clazz.findAll(FieldDeclaration.class)
                                    .forEach(field -> {

                                        String type =
                                                field.getElementType().asString();

                                        if (contains(structure.getServices(), type)) {
                                            info.addService(type);                                        }
                                    });

                        }

                        if (contains(structure.getServices(), className)) {

                            if (!info.getServices().contains(className)) {
                                info.addService(className);;
                            }
                            clazz.findAll(FieldDeclaration.class)
                                    .forEach(field -> {

                                        String type =
                                                field.getElementType().asString();

                                        if (contains(structure.getRepositories(), type)) {
                                            info.addRepository(type);
                                        }

                                    });

                        }

                        if (info.getController() != null
                                || !info.getServices().isEmpty()) {

                            structure.getServiceCalls().add(info);

                        }

                    });

        } catch (Exception ignored) {
        }

    }

    private boolean contains(
            java.util.List<ComponentInfo> components,
            String className) {

        return components.stream()
                .anyMatch(c -> c.getName().equals(className));

    }

}