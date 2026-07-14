package com.ekos.report.sections;

import com.ekos.discovery.model.DiscoveryResult;
import com.ekos.discovery.model.ModuleInfo;

public class ModuleSection {

    public String build(DiscoveryResult result) {

        StringBuilder html = new StringBuilder();

        html.append("<div class='card'>");

        html.append("<h2>Business Modules</h2>");

        html.append("<table>");

        html.append("<tr>");
        html.append("<th>Module</th>");
        html.append("<th>Controllers</th>");
        html.append("<th>Services</th>");
        html.append("<th>Repositories</th>");
        html.append("<th>Entities</th>");
        html.append("<th>Configurations</th>");
        html.append("</tr>");

        for (ModuleInfo module : result.getStructure().getModules()) {

            html.append("<tr>");

            html.append("<td>")
                    .append(module.getName())
                    .append("</td>");

            html.append("<td>")
                    .append(module.getControllers())
                    .append("</td>");

            html.append("<td>")
                    .append(module.getServices())
                    .append("</td>");

            html.append("<td>")
                    .append(module.getRepositories())
                    .append("</td>");

            html.append("<td>")
                    .append(module.getEntities())
                    .append("</td>");

            html.append("<td>")
                    .append(module.getConfigurations())
                    .append("</td>");

            html.append("</tr>");
        }

        html.append("</table>");

        html.append("</div>");

        return html.toString();
    }
}