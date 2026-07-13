package com.ekos.plugins.backend.java;

import com.ekos.discovery.model.ComponentInfo;
import com.ekos.discovery.model.ProjectStructure;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import java.nio.file.Path;

public class EntityScanner extends AbstractJavaScanner {

    public void scan(Path projectRoot,
                     ProjectStructure structure) {

        scanJavaFiles(projectRoot,
                (cu, file) -> processCompilationUnit(cu, file , structure));

    }

    private void processCompilationUnit(
            CompilationUnit cu,
            Path file, ProjectStructure structure) {

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