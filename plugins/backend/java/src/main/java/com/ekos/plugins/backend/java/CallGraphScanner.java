package com.ekos.plugins.backend.java;

import com.ekos.discovery.model.ComponentInfo;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class CallGraphScanner {

    public void scan(Path projectRoot) {

        try (Stream<Path> files = Files.walk(projectRoot)) {

            files.filter(f -> f.toString().endsWith(".java"))
                    .forEach(this::parseJavaFile);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void parseJavaFile(Path file) {

        try {

            CompilationUnit cu = StaticJavaParser.parse(file);

            cu.findAll(ClassOrInterfaceDeclaration.class)
                    .forEach(this::processClass);

        } catch (Exception ignored) {
        }

    }

    private void processClass(ClassOrInterfaceDeclaration clazz) {

        System.out.println(clazz.getNameAsString());

    }

}