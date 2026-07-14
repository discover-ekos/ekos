package com.ekos.plugins.backend.java.scanner;

import com.ekos.discovery.model.PackageInfo;
import com.ekos.discovery.model.ProjectStructure;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class PackageScanner extends AbstractJavaScanner{

    public void scan(Path projectRoot,
                     ProjectStructure structure) {

        Map<String, PackageInfo> packages = new HashMap<>();

        scanJavaFiles(projectRoot,
                (cu, file) -> process(cu, packages));

        structure.getPackages().clear();

        structure.getPackages().addAll(packages.values());

    }

    private void process(CompilationUnit cu,
                         Map<String, PackageInfo> packages) {

        try {

            String packageName =
                    cu.getPackageDeclaration()
                            .map(pd -> pd.getNameAsString())
                            .orElse("default");

            PackageInfo info =
                    packages.computeIfAbsent(packageName, p -> {

                        PackageInfo pi = new PackageInfo();

                        pi.setPackageName(packageName);

                        return pi;

                    });

            info.setClassCount(info.getClassCount() + 1);

            cu.findAll(ClassOrInterfaceDeclaration.class)
                    .forEach(clazz -> {

                        if (clazz.isAnnotationPresent("RestController")
                                || clazz.isAnnotationPresent("Controller")) {

                            info.setControllerCount(
                                    info.getControllerCount() + 1);

                        }

                        if (clazz.isAnnotationPresent("Service")) {

                            info.setServiceCount(
                                    info.getServiceCount() + 1);

                        }

                        if (clazz.isAnnotationPresent("Repository")) {

                            info.setRepositoryCount(
                                    info.getRepositoryCount() + 1);

                        }

                        if (clazz.isAnnotationPresent("Entity")) {

                            info.setEntityCount(
                                    info.getEntityCount() + 1);

                        }

                        if (clazz.isAnnotationPresent("Configuration")) {

                            info.setConfigurationCount(
                                    info.getConfigurationCount() + 1);

                        }

                    });



        } catch (Exception ignored) {
        }

    }

}