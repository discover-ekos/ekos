package com.ekos.report.generator;

import com.ekos.discovery.model.ApiInventory;
import com.ekos.discovery.model.DiscoveryResult;
import com.ekos.report.model.RequestFlow;

import java.util.ArrayList;
import java.util.List;

public class RequestFlowGenerator {

    public List<RequestFlow> generate(DiscoveryResult result) {

        List<RequestFlow> flows = new ArrayList<>();

        for (ApiInventory api : result.getStructure().getApiInventory()) {

            RequestFlow flow = new RequestFlow();

            flow.setApi(api.getMethod() + " " + api.getPath());

            flow.addStep(api.getController());

            if (!"Unknown".equals(api.getService())) {
                flow.addStep(api.getService());
            }

            if (!"Unknown".equals(api.getRepository())) {
                flow.addStep(api.getRepository());
            }

            flow.addStep("Database");

            flows.add(flow);

        }

        return flows;

    }

}