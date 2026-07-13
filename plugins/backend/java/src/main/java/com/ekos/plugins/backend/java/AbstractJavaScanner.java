package com.ekos.plugins.backend.java;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public abstract class AbstractJavaScanner {

    protected void scanJavaFiles(Path projectRoot,
                                 CompilationUnitConsumer consumer) {

        try (Stream<Path> files = Files.walk(projectRoot)) {

            files.filter(file -> file.toString().endsWith(".java"))
                    .forEach(file -> {

                        try {

                            CompilationUnit cu =
                                    StaticJavaParser.parse(file);

                            consumer.accept(cu, file);

                        } catch (Exception ignored) {
                        }

                    });

        } catch (IOException ignored) {
        }

    }

}