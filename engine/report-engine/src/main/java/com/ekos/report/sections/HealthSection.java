package com.ekos.report.sections;

import com.ekos.discovery.model.DiscoveryResult;

public class HealthSection {

    public String build(DiscoveryResult result) {

        StringBuilder html = new StringBuilder();

        html.append("<div class='card'>");

        html.append("<h2>Project Health</h2>");

        html.append("<table>");

        addRow(html, "Overall Score",
                result.getHealthScore().getOverall());

        addRow(html, "Architecture",
                result.getHealthScore().getArchitecture());

        addRow(html, "Code Quality",
                result.getHealthScore().getCodeQuality());

        addRow(html, "Maintainability",
                result.getHealthScore().getMaintainability());

        html.append("</table>");

        long high = result.getArchitectureIssues().stream()
                .filter(i -> "HIGH".equals(i.getSeverity()))
                .count();

        long medium = result.getArchitectureIssues().stream()
                .filter(i -> "MEDIUM".equals(i.getSeverity()))
                .count();

        long low = result.getArchitectureIssues().stream()
                .filter(i -> "LOW".equals(i.getSeverity()))
                .count();

        html.append("<h3>Issue Summary</h3>");

        html.append("<table>");

        addRow(html, "HIGH", (int) high);
        addRow(html, "MEDIUM", (int) medium);
        addRow(html, "LOW", (int) low);

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