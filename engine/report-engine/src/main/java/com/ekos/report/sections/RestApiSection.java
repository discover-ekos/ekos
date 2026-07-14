package com.ekos.report.sections;

import com.ekos.discovery.model.ApiInventory;
import com.ekos.discovery.model.DiscoveryResult;

public class RestApiSection {

    public String build(DiscoveryResult result) {
        StringBuilder html = new StringBuilder();

        html.append("<div class='card'>");

        html.append("<h2>REST API Inventory</h2>");

        html.append("""
                <input
                    type="text"
                    id="apiSearch"
                    placeholder="Search API..."
                    onkeyup="searchApi()"
                    style="width:100%;padding:10px;margin-bottom:10px;">
                """);

        html.append("<table id='apiTable'>");

        html.append("<tr>");

        html.append("<th>Method</th>");
        html.append("<th>Path</th>");
        html.append("<th>Controller</th>");
        html.append("<th>Service</th>");
        html.append("<th>Repository</th>");

        html.append("</tr>");

        for (
                ApiInventory api : result.getStructure().getApiInventory()) {

            html.append("<tr>");

            html.append("<td>")
                    .append(api.getMethod())
                    .append("</td>");

            html.append("<td>")
                    .append(api.getPath())
                    .append("</td>");

            html.append("<td>")
                    .append(api.getController())
                    .append("</td>");

            html.append("<td>")
                    .append(api.getService())
                    .append("</td>");

            html.append("<td>")
                    .append(api.getRepository())
                    .append("</td>");

            html.append("</tr>");

        }

        html.append("</table>");

        html.append("</div>");

        return html.toString();
    }
}
