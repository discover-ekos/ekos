package com.ekos.report.sections;

import com.ekos.discovery.model.DiscoveryResult;
import com.ekos.report.generator.RequestFlowGenerator;
import com.ekos.report.model.RequestFlow;

public class RequestFlowSection {

    public String build(DiscoveryResult result) {

        RequestFlowGenerator generator =
                new RequestFlowGenerator();

        StringBuilder html = new StringBuilder();

        html.append("<div class='card'>");

        html.append("<h2>Request Flow Explorer</h2>");

        for (RequestFlow flow : generator.generate(result)) {

            html.append("<h3>")
                    .append(flow.getApi())
                    .append("</h3>");

            html.append("<ul>");

            for (String step : flow.getSteps()) {

                html.append("<li>")
                        .append(step)
                        .append("</li>");

            }

            html.append("</ul>");

        }

        html.append("</div>");

        return html.toString();

    }
}
