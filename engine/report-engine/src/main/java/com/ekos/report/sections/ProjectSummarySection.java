package com.ekos.report.sections;

import com.ekos.discovery.model.DiscoveryResult;

public class ProjectSummarySection {

    public String build(DiscoveryResult result) {

        StringBuilder html = new StringBuilder();

        html.append("<div class='card'>");

        html.append("<h1>EKOS Architecture Report</h1>");

        html.append("<table>");

        html.append("<tr><th>Project</th><td>")
                .append(result.getProjectName())
                .append("</td></tr>");

        html.append("<tr><th>Framework</th><td>")
                .append(result.getMetadata().getFramework())
                .append("</td></tr>");

        html.append("<tr><th>Java Version</th><td>")
                .append(result.getMetadata().getJavaVersion())
                .append("</td></tr>");

        html.append("<tr><th>Build Tool</th><td>")
                .append(result.getMetadata().getBuildTool())
                .append("</td></tr>");

        html.append("</table>");

        html.append("</div>");

        return html.toString();
    }

}