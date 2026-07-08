package com.ekos.plugins.backend.java;

import com.ekos.discovery.model.DependencyRelation;
import com.ekos.discovery.model.ProjectStructure;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class DependencyDiscoveryStep {

    public void scan(Path projectRoot,
                     ProjectStructure structure) {

        try (Stream<Path> files = Files.walk(projectRoot)) {

            files.filter(f -> f.toString().endsWith(".java"))
                    .forEach(file -> process(file, structure));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void process(Path javaFile,
                         ProjectStructure structure) {

        try {

            CompilationUnit cu = StaticJavaParser.parse(javaFile);

            cu.findAll(ClassOrInterfaceDeclaration.class)
                    .forEach(clazz -> {

                        String source = clazz.getNameAsString();

                        clazz.findAll(FieldDeclaration.class)
                                .forEach(field -> {

                                    String target =
                                            field.getElementType().asString();

                                    DependencyRelation relation =
                                            new DependencyRelation();

                                    relation.setSource(source);
                                    relation.setTarget(target);
                                    relation.setType("FIELD_INJECTION");

                                    structure.getDependencies()
                                            .add(relation);

                                });

                    });

        } catch (Exception ignored) {
        }

    }

}