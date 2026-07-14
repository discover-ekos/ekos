package com.ekos.report.sections;

import com.ekos.discovery.model.DiscoveryResult;

public class TechnologySection {

    public String build(DiscoveryResult result) {

        StringBuilder html = new StringBuilder();

        html.append("<div class='card'>");

        html.append("<h2>Technology Stack</h2>");

        html.append("<table>");

        html.append("<tr>");
        html.append("<th>Technology</th>");
        html.append("<th>Version</th>");
        html.append("</tr>");

        result.getTechnologies().forEach(t -> {

            html.append("<tr>");

            html.append("<td>")
                    .append(t.getName())
                    .append("</td>");

            html.append("<td>")
                    .append(t.getVersion() == null ? "-" : t.getVersion())
                    .append("</td>");

            html.append("</tr>");

        });

        html.append("</table>");

        html.append("</div>");

        return html.toString();

    }

}