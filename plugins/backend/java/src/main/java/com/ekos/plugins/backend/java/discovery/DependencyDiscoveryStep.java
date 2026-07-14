package com.ekos.plugins.backend.java.discovery;

import com.ekos.discovery.model.DependencyRelation;
import com.ekos.discovery.model.ProjectStructure;
import com.ekos.plugins.backend.java.scanner.AbstractJavaScanner;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;

import java.nio.file.Path;

public class DependencyDiscoveryStep extends AbstractJavaScanner {

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