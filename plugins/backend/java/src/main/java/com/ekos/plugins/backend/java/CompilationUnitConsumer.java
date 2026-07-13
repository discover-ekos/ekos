package com.ekos.plugins.backend.java;

import com.github.javaparser.ast.CompilationUnit;

import java.nio.file.Path;

@FunctionalInterface
public interface CompilationUnitConsumer {

    void accept(CompilationUnit compilationUnit,
                Path javaFile);

}