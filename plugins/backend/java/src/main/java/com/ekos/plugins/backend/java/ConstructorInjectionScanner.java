package com.ekos.plugins.backend.java;

import com.ekos.discovery.model.ComponentRelation;
import com.ekos.discovery.model.ProjectStructure;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class ConstructorInjectionScanner extends AbstractJavaScanner {

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

                        String source =
                                clazz.getNameAsString();

                        discoverConstructorInjection(
                                clazz,
                                source,
                                structure);

                        discoverFieldInjection(
                                clazz,
                                source,
                                structure);

                    });

        } catch (Exception ignored) {
        }

    }

    private void discoverConstructorInjection(
            ClassOrInterfaceDeclaration clazz,
            String source,
            ProjectStructure structure) {

        clazz.findAll(ConstructorDeclaration.class)
                .forEach(constructor ->

                        constructor.getParameters()
                                .forEach(parameter -> {

                                    ComponentRelation relation =
                                            new ComponentRelation();

                                    relation.setSource(source);

                                    relation.setTarget(
                                            parameter.getType().asString());

                                    relation.setRelationType(
                                            "CONSTRUCTOR_INJECTION");

                                    structure.getComponentRelations()
                                            .add(relation);

                                }));

    }

    private void discoverFieldInjection(
            ClassOrInterfaceDeclaration clazz,
            String source,
            ProjectStructure structure) {

        clazz.findAll(FieldDeclaration.class)
                .forEach(field -> {

                    if (!field.isAnnotationPresent("Autowired")) {
                        return;
                    }

                    ComponentRelation relation =
                            new ComponentRelation();

                    relation.setSource(source);

                    relation.setTarget(
                            field.getElementType().asString());

                    relation.setRelationType(
                            "FIELD_INJECTION");

                    structure.getComponentRelations()
                            .add(relation);

                });

    }

}