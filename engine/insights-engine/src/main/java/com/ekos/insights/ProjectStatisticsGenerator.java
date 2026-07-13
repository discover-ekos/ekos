package com.ekos.insights;

import com.ekos.discovery.model.DiscoveryResult;
import com.ekos.discovery.model.ProjectStatistics;

public class ProjectStatisticsGenerator {

    public void generate(DiscoveryResult result) {

        ProjectStatistics statistics = result.getStatistics();

        statistics.setTotalControllers(
                result.getStructure().getControllers().size());

        statistics.setTotalServices(
                result.getStructure().getServices().size());

        statistics.setTotalRepositories(
                result.getStructure().getRepositories().size());

        statistics.setTotalApis(
                result.getStructure().getApiInventory().size());

        statistics.setTotalTechnologies(
                result.getTechnologies().size());

    }

}