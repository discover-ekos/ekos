package com.ekos.report.sections;

import com.ekos.report.analyzer.model.AnalysisResult;
import com.ekos.report.analyzer.model.DeadComponent;

public class DeadCodeSection {

    public String build(AnalysisResult analysis) {

        StringBuilder html = new StringBuilder();

        html.append("<div class='card'>");

        html.append("<h2>Dead Code Analysis</h2>");

        html.append("<table>");

        html.append("""
            <tr>
                <th>Component</th>
                <th>Type</th>
                <th>Reason</th>
            </tr>
            """);

        for (DeadComponent dead :
                analysis.getDeadComponents()) {

            html.append("<tr>");

            html.append("<td>")
                    .append(dead.getComponentName())
                    .append("</td>");

            html.append("<td>")
                    .append(dead.getComponentType())
                    .append("</td>");

            html.append("<td>")
                    .append(dead.getReason())
                    .append("</td>");

            html.append("</tr>");
        }

        html.append("</table>");

        html.append("</div>");

        return html.toString();
    }

}