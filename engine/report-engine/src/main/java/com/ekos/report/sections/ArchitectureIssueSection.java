package com.ekos.report.sections;

import com.ekos.discovery.model.ArchitectureIssue;
import com.ekos.discovery.model.DiscoveryResult;

public class ArchitectureIssueSection {

    public String build(DiscoveryResult result) {

        StringBuilder html = new StringBuilder();

        html.append("<div class='card'>");

        html.append("<h2>Architecture Issues</h2>");

        if (result.getArchitectureIssues().isEmpty()) {

            html.append("<p>No architecture issues found.</p>");
            html.append("</div>");

            return html.toString();
        }

        html.append("<table>");

        html.append("<tr>");
        html.append("<th>Severity</th>");
        html.append("<th>Category</th>");
        html.append("<th>Description</th>");
        html.append("</tr>");

        for (ArchitectureIssue issue : result.getArchitectureIssues()) {

            html.append("<tr>");

            html.append("<td>")
                    .append(issue.getSeverity())
                    .append("</td>");

            html.append("<td>")
                    .append(issue.getCategory())
                    .append("</td>");

            html.append("<td>")
                    .append(issue.getDescription())
                    .append("</td>");

            html.append("</tr>");
        }

        html.append("</table>");

        html.append("</div>");

        return html.toString();
    }
}