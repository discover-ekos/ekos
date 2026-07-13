package com.ekos.plugins.backend.java;

import com.ekos.discovery.model.ApiInventory;
import com.ekos.discovery.model.ProjectStructure;
import com.ekos.discovery.model.RestEndpoint;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class RestApiDiscoveryStep extends AbstractJavaScanner {

    public void scan(Path projectRoot, ProjectStructure structure) {

        scanJavaFiles(projectRoot,
                (cu, file) -> processCompilationUnit(cu, structure));

    }

    private void processCompilationUnit(
            CompilationUnit cu,
            ProjectStructure structure) {

        try {

            cu.findAll(ClassOrInterfaceDeclaration.class)
                    .forEach(clazz -> {

                        if (!isRestController(clazz)) {
                            return;
                        }

                        String basePath = getBasePath(clazz);

                        clazz.findAll(MethodDeclaration.class)
                                .forEach(method -> {

                                    RestEndpoint endpoint =
                                            buildEndpoint(
                                                    structure,
                                                    clazz.getNameAsString(),
                                                    basePath,
                                                    method
                                            );

                                    if (endpoint != null) {
                                        structure.getRestEndpoints().add(endpoint);
                                    }

                                });

                    });

        } catch (Exception ignored) {
        }

    }

    private boolean isRestController(ClassOrInterfaceDeclaration clazz) {

        return clazz.getAnnotations()
                .stream()
                .map(AnnotationExpr::getNameAsString)
                .anyMatch(name ->
                        name.equals("RestController")
                                || name.equals("Controller"));

    }

    private String getBasePath(ClassOrInterfaceDeclaration clazz) {

        return clazz.getAnnotationByName("RequestMapping")
                .map(annotation -> annotation.toString())
                .map(this::extractPath)
                .orElse("");

    }

    private RestEndpoint buildEndpoint(ProjectStructure structure,
                                       String controller,
                                       String basePath,
                                       MethodDeclaration method){

        for (AnnotationExpr annotation : method.getAnnotations()) {

            String annotationName = annotation.getNameAsString();

            String httpMethod = switch (annotationName) {

                case "GetMapping" -> "GET";

                case "PostMapping" -> "POST";

                case "PutMapping" -> "PUT";

                case "DeleteMapping" -> "DELETE";

                case "PatchMapping" -> "PATCH";

                default -> null;

            };

            if (httpMethod == null) {
                continue;
            }

            RestEndpoint endpoint = new RestEndpoint();

            endpoint.setController(controller);

            endpoint.setMethod(httpMethod);

            endpoint.setJavaMethod(method.getNameAsString());

            endpoint.setPath(basePath + extractPath(annotation.toString()));

            ApiInventory api = new ApiInventory();

            api.setMethod(httpMethod);

            api.setPath(basePath + extractPath(annotation.toString()));

            api.setController(controller);

            api.setJavaMethod(method.getNameAsString());

            api.setProduces("application/json");

            api.setConsumes("application/json");

            endpoint.setController(controller);

            endpoint.setMethod(httpMethod);

            endpoint.setJavaMethod(method.getNameAsString());

            endpoint.setPath(basePath + extractPath(annotation.toString()));

            structure.getServiceCalls()
                    .stream()
                    .filter(c -> controller.equals(c.getController()))
                    .findFirst()
                    .ifPresent(call -> {

                        if (!call.getServices().isEmpty()) {
                            api.setService(String.join(", ", call.getServices()));
                        }

                        if (!call.getRepositories().isEmpty()) {
                            api.setRepository(String.join(", ", call.getRepositories()));
                        }

                    });

            if (api.getService() == null) {
                api.setService("Unknown");
            }

            if (api.getRepository() == null) {
                api.setRepository("Unknown");
            }

            structure.getApiInventory().add(api);

            return endpoint;

        }

        return null;

    }

    private String extractPath(String annotation) {

        int firstQuote = annotation.indexOf("\"");

        if (firstQuote == -1) {
            return "";
        }

        int secondQuote = annotation.indexOf("\"", firstQuote + 1);

        if (secondQuote == -1) {
            return "";
        }

        return annotation.substring(firstQuote + 1, secondQuote);

    }

}