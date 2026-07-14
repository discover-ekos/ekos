package com.ekos.report.sections;

import com.ekos.discovery.model.DiscoveryResult;
import com.ekos.discovery.model.ServiceCallInfo;

public class DependencySection {

    public String build(DiscoveryResult result) {

        StringBuilder html = new StringBuilder();

        html.append("<div class='card'>");

        html.append("<h2>Dependency Explorer</h2>");

        html.append("<table>");

        html.append("<tr>");
        html.append("<th>Controller</th>");
        html.append("<th>Services</th>");
        html.append("<th>Repositories</th>");
        html.append("</tr>");

        for (ServiceCallInfo call :
                result.getStructure().getServiceCalls()) {

            html.append("<tr>");

            html.append("<td>")
                    .append(call.getController())
                    .append("</td>");

            html.append("<td>")
                    .append(String.join(", ", call.getServices()))
                    .append("</td>");

            html.append("<td>")
                    .append(String.join(", ", call.getRepositories()))
                    .append("</td>");

            html.append("</tr>");

        }

        html.append("</table>");

        html.append("</div>");

        return html.toString();

    }

}