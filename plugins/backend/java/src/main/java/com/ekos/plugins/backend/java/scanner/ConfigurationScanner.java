package com.ekos.plugins.backend.java.scanner;

import com.ekos.discovery.model.ComponentInfo;
import com.ekos.discovery.model.ProjectStructure;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import java.nio.file.Path;

public class ConfigurationScanner extends AbstractJavaScanner {

    public void scan(Path projectRoot,
                     ProjectStructure structure) {

        scanJavaFiles(projectRoot,
                (cu, file) -> processCompilationUnit(cu, file, structure));

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

                        if (!(clazz.isAnnotationPresent("Configuration")
                                || clazz.isAnnotationPresent("ConfigurationProperties")
                                || clazz.isAnnotationPresent("Component")
                                || clazz.isAnnotationPresent("EnableScheduling")
                                || clazz.isAnnotationPresent("EnableCaching")
                                || clazz.isAnnotationPresent("EnableJpaRepositories"))) {
                            return;
                        }

                        ComponentInfo config = new ComponentInfo();

                        config.setName(clazz.getNameAsString());
                        config.setPackageName(packageName);
                        config.setType("CONFIGURATION");
                        config.setFilePath(file.toString());

                        structure.getConfigurations().add(config);

                    });

        } catch (Exception ignored) {
        }

    }
}