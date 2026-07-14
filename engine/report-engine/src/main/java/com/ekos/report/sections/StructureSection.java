package com.ekos.report.sections;

import com.ekos.discovery.model.DiscoveryResult;

public class StructureSection {

    public String build(DiscoveryResult result) {

        StringBuilder html = new StringBuilder();

        html.append("<div class='card'>");

        html.append("<h2>Project Structure</h2>");

        html.append("<table>");

        addRow(html, "Controllers",
                result.getStructure().getControllerCount());

        addRow(html, "Services",
                result.getStructure().getServiceCount());

        addRow(html, "Repositories",
                result.getStructure().getRepositoryCount());

        addRow(html, "Entities",
                result.getStructure().getEntityCount());

        addRow(html, "Configurations",
                result.getStructure().getConfigurations().size());

        addRow(html, "REST APIs",
                result.getStructure().getRestEndpointCount());

        addRow(html, "Packages",
                result.getStructure().getPackages().size());

        addRow(html, "Modules",
                result.getStructure().getModules().size());

        html.append("</table>");

        html.append("</div>");

        return html.toString();
    }

    private void addRow(StringBuilder html,
                        String label,
                        int value) {

        html.append("<tr>");

        html.append("<th>")
                .append(label)
                .append("</th>");

        html.append("<td>")
                .append(value)
                .append("</td>");

        html.append("</tr>");
    }
}