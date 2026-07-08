package com.ekos.plugins.backend.java;

import com.ekos.discovery.model.ProjectStructure;
import com.ekos.discovery.model.RepositoryInfo;
import com.ekos.discovery.model.TableInfo;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class DatabaseScanner {

    private final DatasourceDetector datasourceDetector =
            new DatasourceDetector();

    public void scan(Path projectRoot,
                     ProjectStructure structure) {

        structure.getDatabase().setDatabaseType(
                datasourceDetector.detect(projectRoot)
        );

        try (Stream<Path> files = Files.walk(projectRoot)) {

            files.filter(f -> f.toString().endsWith(".java"))
                    .forEach(file -> processJavaFile(file, structure));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void processJavaFile(Path javaFile,
                                 ProjectStructure structure) {

        try {

            CompilationUnit cu = StaticJavaParser.parse(javaFile);

            cu.findAll(ClassOrInterfaceDeclaration.class)
                    .forEach(clazz -> {

                        discoverEntity(clazz, structure);

                        discoverRepository(clazz, structure);

                    });

        } catch (Exception ignored) {
        }

    }

    private String getTableName(ClassOrInterfaceDeclaration clazz) {

        if (clazz.getAnnotationByName("Table").isPresent()) {

            String text = clazz.getAnnotationByName("Table")
                    .get()
                    .toString();

            int first = text.indexOf("\"");

            if (first != -1) {

                int second = text.indexOf("\"", first + 1);

                if (second != -1) {
                    return text.substring(first + 1, second);
                }
            }
        }

        return clazz.getNameAsString();

    }

    private void discoverEntity(ClassOrInterfaceDeclaration clazz,
                                ProjectStructure structure) {

        if (!clazz.isAnnotationPresent("Entity")) {
            return;
        }

        TableInfo table = new TableInfo();

        table.setEntityName(clazz.getNameAsString());

        table.setTableName(getTableName(clazz));

        structure.getDatabase()
                .getTables()
                .add(table);

    }

    private void discoverRepository(ClassOrInterfaceDeclaration clazz,
                                    ProjectStructure structure) {

        if (!clazz.isInterface()) {
            return;
        }

        boolean repository = clazz.getExtendedTypes()
                .stream()
                .anyMatch(type -> {

                    String name = type.getNameAsString();

                    return name.equals("JpaRepository")
                            || name.equals("CrudRepository")
                            || name.equals("PagingAndSortingRepository");

                });

        if (!repository) {
            return;
        }

        RepositoryInfo info = new RepositoryInfo();

        info.setRepositoryName(clazz.getNameAsString());

        info.setRepositoryType(
                clazz.getExtendedTypes().get(0).getNameAsString()
        );

        structure.getDatabase()
                .getRepositories()
                .add(info);

    }

}