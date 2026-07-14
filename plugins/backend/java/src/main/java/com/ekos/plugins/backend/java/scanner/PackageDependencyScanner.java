package com.ekos.plugins.backend.java.scanner;

import com.ekos.discovery.model.DependencyRelation;
import com.ekos.discovery.model.ProjectStructure;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;

import java.nio.file.Path;

public class PackageDependencyScanner extends AbstractJavaScanner{

    public void scan(Path projectRoot,
                     ProjectStructure structure) {

        scanJavaFiles(projectRoot,
                (cu, file) -> process(cu, structure));

    }

    private void process(CompilationUnit cu,
                         ProjectStructure structure) {

        try {

            String sourcePackage =
                    cu.getPackageDeclaration()
                            .map(pd -> pd.getNameAsString())
                            .orElse("");

            cu.findAll(ImportDeclaration.class)
                    .forEach(importDecl -> {

                        DependencyRelation relation =
                                new DependencyRelation();

                        relation.setSource(sourcePackage);

                        relation.setTarget(
                                importDecl.getNameAsString());

                        relation.setType("IMPORT");

                        structure.getDependencies()
                                .add(relation);

                    });

        } catch (Exception ignored) {
        }

    }

}