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

public class EntityScanner extends AbstractJavaScanner {

    public void scan(Path projectRoot,
                     ProjectStructure structure) {

        scanJavaFiles(projectRoot,
                (cu, file) -> processCompilationUnit(cu, structure));

    }

    private void processCompilationUnit(
            CompilationUnit cu,
            ProjectStructure structure) {

        try {

            String packageName = cu.getPackageDeclaration()
                    .map(pd -> pd.getNameAsString())
                    .orElse("");

            cu.findAll(ClassOrInterfaceDeclaration.class)
                    .forEach(clazz -> {

                        if (!clazz.isAnnotationPresent("Entity")) {
                            return;
                        }

                        ComponentInfo entity = new ComponentInfo();

                        entity.setName(clazz.getNameAsString());
                        entity.setPackageName(packageName);
                        entity.setType("ENTITY");
                        entity.setFilePath(file.toString());

                        structure.getEntities().add(entity);

                    });

        } catch (Exception ignored) {
        }

    }
}