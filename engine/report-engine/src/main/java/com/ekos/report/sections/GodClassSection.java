package com.ekos.report.sections;

import com.ekos.report.analyzer.model.AnalysisResult;
import com.ekos.report.analyzer.model.GodClass;

public class GodClassSection {

    public String build(AnalysisResult analysis) {

        StringBuilder html = new StringBuilder();

        html.append("<div class='card'>");

        html.append("<h2>Potential God Classes</h2>");

        html.append("<table>");

        html.append("""
            <tr>
                <th>Class</th>
            </tr>
            """);

        for (GodClass god : analysis.getGodClasses()) {

            html.append("<tr>");

            html.append("<td>")
                    .append(god.getClassName())
                    .append("</td>");

            html.append("<td>")
                    .append(god.getMethodCount())
                    .append("</td>");

            html.append("<td>")
                    .append(god.getFieldCount())
                    .append("</td>");

            html.append("<td>")
                    .append(god.getDependencyCount())
                    .append("</td>");

            html.append("<td>")
                    .append(god.getScore())
                    .append("</td>");

            html.append("<td>")
                    .append(risk(god.getScore()))
                    .append("</td>");

            html.append("</tr>");
        }

        html.append("</table>");

        html.append("</div>");

        return html.toString();

    }

    private String risk(int score) {

        if (score >= 120)
            return "🔴 Critical";

        if (score >= 90)
            return "🟠 High";

        if (score >= 60)
            return "🟡 Medium";

        return "🟢 Low";

    }

}