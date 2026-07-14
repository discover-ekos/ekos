package com.ekos.report.analyzer;

import com.ekos.discovery.model.ComponentInfo;
import com.ekos.discovery.model.DiscoveryResult;
import com.ekos.discovery.model.RestEndpoint;
import com.ekos.discovery.model.ServiceCallInfo;
import com.ekos.report.analyzer.model.AnalysisResult;
import com.ekos.report.analyzer.model.DeadComponent;

public class DeadCodeAnalyzer {

    public void analyze(DiscoveryResult discovery,
                        AnalysisResult analysis) {

        detectUnusedControllers(discovery, analysis);

        detectUnusedServices(discovery, analysis);

        detectUnusedRepositories(discovery, analysis);
    }

    private void detectUnusedControllers(
            DiscoveryResult discovery,
            AnalysisResult analysis) {

        for (ComponentInfo controller :
                discovery.getStructure().getControllers()) {

            boolean used = discovery.getStructure()
                    .getRestEndpoints()
                    .stream()
                    .map(RestEndpoint::getController)
                    .anyMatch(controller.getName()::equals);

            if (!used) {

                DeadComponent dead = new DeadComponent();

                dead.setComponentName(controller.getName());

                dead.setComponentType("Controller");

                dead.setReason("No REST endpoints found");

                analysis.getDeadComponents().add(dead);
            }
        }

    }

    private void detectUnusedServices(
            DiscoveryResult discovery,
            AnalysisResult analysis) {

        for (ComponentInfo service :
                discovery.getStructure().getServices()) {

            boolean used = discovery.getStructure()
                    .getServiceCalls()
                    .stream()
                    .anyMatch(call ->
                            call.getServices()
                                    .contains(service.getName()));

            if (!used) {

                DeadComponent dead = new DeadComponent();

                dead.setComponentName(service.getName());

                dead.setComponentType("Service");

                dead.setReason("Never injected");

                analysis.getDeadComponents().add(dead);
            }
        }

    }

    private void detectUnusedRepositories(
            DiscoveryResult discovery,
            AnalysisResult analysis) {

        for (ComponentInfo repository :
                discovery.getStructure().getRepositories()) {

            boolean used = discovery.getStructure()
                    .getServiceCalls()
                    .stream()
                    .anyMatch(call ->
                            call.getRepositories()
                                    .contains(repository.getName()));

            if (!used) {

                DeadComponent dead = new DeadComponent();

                dead.setComponentName(repository.getName());

                dead.setComponentType("Repository");

                dead.setReason("Never injected");

                analysis.getDeadComponents().add(dead);
            }
        }

    }

}