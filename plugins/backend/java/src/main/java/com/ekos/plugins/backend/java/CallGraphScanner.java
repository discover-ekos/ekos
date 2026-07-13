package com.ekos.plugins.backend.java;

import com.ekos.discovery.model.ComponentInfo;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class CallGraphScanner extends AbstractJavaScanner {

    public void scan(Path projectRoot) {

        scanJavaFiles(projectRoot,
                this::processCompilationUnit);

    }

    private void processCompilationUnit(
            CompilationUnit cu,
            Path file) {

        try {


            cu.findAll(ClassOrInterfaceDeclaration.class)
                    .forEach(this::processClass);

        } catch (Exception ignored) {
        }

    }

    private void processClass(ClassOrInterfaceDeclaration clazz) {

        System.out.println(clazz.getNameAsString());

    }

}