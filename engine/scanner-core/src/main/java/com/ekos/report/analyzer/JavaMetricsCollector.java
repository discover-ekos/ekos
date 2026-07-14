package com.ekos.report.analyzer;

import com.ekos.report.analyzer.model.JavaMetrics;
import com.ekos.report.analyzer.repository.MetricsRepository;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.io.File;
import java.nio.file.Files;

public class JavaMetricsCollector {

    private final MetricsRepository repository =
            new MetricsRepository();

    public JavaMetrics collect(String filePath) {

        JavaMetrics metrics = new JavaMetrics();

        try {

            CompilationUnit cu =
                    StaticJavaParser.parse(new File(filePath));

            ClassOrInterfaceDeclaration clazz =
                    cu.findFirst(ClassOrInterfaceDeclaration.class)
                            .orElse(null);

            if (clazz == null)
                return metrics;

            metrics.setClassName(clazz.getNameAsString());

            metrics.setMethodCount(
                    clazz.findAll(MethodDeclaration.class).size());

            metrics.setFieldCount(
                    clazz.findAll(FieldDeclaration.class).size());

            metrics.setConstructorCount(
                    clazz.findAll(ConstructorDeclaration.class).size());

            metrics.setPublicMethodCount(
                    (int) clazz.findAll(MethodDeclaration.class)
                            .stream()
                            .filter(MethodDeclaration::isPublic)
                            .count());

            metrics.setPrivateMethodCount(
                    (int) clazz.findAll(MethodDeclaration.class)
                            .stream()
                            .filter(MethodDeclaration::isPrivate)
                            .count());

            int dependencies = 0;

            for (FieldDeclaration field : clazz.findAll(FieldDeclaration.class)) {

                String type = field.getElementType().asString();

                if (isDependency(type)) {
                    dependencies++;
                }
            }

            metrics.setDependencyCount(dependencies);

            metrics.setLineCount(
                    Files.readAllLines(new File(filePath).toPath()).size());

        } catch (Exception ignored) {
        }

        repository.add(metrics);

        return metrics;
    }

    private boolean isDependency(String type) {

        return !(

                type.equals("String")

                        || type.equals("Integer")

                        || type.equals("Long")

                        || type.equals("Double")

                        || type.equals("Float")

                        || type.equals("Boolean")

                        || type.equals("Character")

                        || type.equals("Byte")

                        || type.equals("Short")

                        || type.equals("int")

                        || type.equals("long")

                        || type.equals("double")

                        || type.equals("float")

                        || type.equals("boolean")

                        || type.equals("char")

                        || type.equals("byte")

                        || type.equals("short")

                        || type.startsWith("List")

                        || type.startsWith("Set")

                        || type.startsWith("Map")

                        || type.startsWith("Optional")

        );

    }

    public MetricsRepository getRepository() {

        return repository;

    }
}