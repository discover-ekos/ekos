package com.ekos.report.sections;

import com.ekos.discovery.model.DependencyHeat;
import com.ekos.discovery.model.DiscoveryResult;

import java.util.Comparator;

public class DependencyHeatmapSection {

    public String build(DiscoveryResult result) {

        StringBuilder html = new StringBuilder();

        html.append("<div class='card'>");

        html.append("<h2>Dependency Heatmap</h2>");

        html.append("<table>");

        html.append("""
            <tr>
                <th>Component</th>
                <th>Incoming</th>
                <th>Outgoing</th>
                <th>Total</th>
            </tr>
            """);

        result.getStructure()
                .getDependencyHeatmap()
                .stream()
                .sorted(Comparator.comparingInt(
                                DependencyHeat::getTotalDependencies)
                        .reversed())
                .forEach(heat -> {

                    html.append("<tr>");

                    html.append("<td>")
                            .append(heat.getComponent())
                            .append("</td>");

                    html.append("<td>")
                            .append(heat.getIncomingDependencies())
                            .append("</td>");

                    html.append("<td>")
                            .append(heat.getOutgoingDependencies())
                            .append("</td>");

                    html.append("<td>")
                            .append(heat.getTotalDependencies())
                            .append("</td>");

                    html.append("</tr>");
                });

        html.append("</table>");

        html.append("</div>");

        return html.toString();
    }
}