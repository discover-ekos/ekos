package com.ekos.report;

import com.ekos.discovery.model.DiscoveryResult;

import com.ekos.report.analyzer.model.AnalysisResult;
import com.ekos.report.sections.*;

public class HtmlReportGenerator {

    public String generate(
            DiscoveryResult result,
            AnalysisResult analysis) {

        StringBuilder html = new StringBuilder();

        html.append("""
        <html>
        <head>
            <title>EKOS Architecture Report</title>

            <style>

                body{
                    font-family:Arial;
                    margin:40px;
                    background:#f7f7f7;
                }

                h1{
                    color:#0d47a1;
                }

                table{
                    width:100%;
                    border-collapse:collapse;
                    margin-bottom:30px;
                }

                td,th{
                    border:1px solid #cccccc;
                    padding:8px;
                }

                th{
                    background:#eeeeee;
                }

                .card{
                    background:white;
                    padding:20px;
                    margin-bottom:20px;
                    border-radius:8px;
                    box-shadow:0 2px 8px rgba(0,0,0,.1);
                }

            </style>

        </head>

        <body>

        """);

        ProjectSummarySection projectSection =
                new ProjectSummarySection();

        html.append(projectSection.build(result));

        TechnologySection technologySection =
                new TechnologySection();

        html.append(technologySection.build(result));
        StructureSection structureSection =
                new StructureSection();

        html.append(structureSection.build(result));
        HealthSection healthSection =
                new HealthSection();

        html.append(healthSection.build(result));
        ArchitectureIssueSection issueSection =
                new ArchitectureIssueSection();

        html.append(issueSection.build(result));
        RestApiSection apiSection =
                new RestApiSection();

        html.append(apiSection.build(result));

        RequestFlowSection flowSection =
                new RequestFlowSection();

        html.append(flowSection.build(result));

        DependencySection dependencyExplorer =
                new DependencySection();

        html.append(dependencyExplorer.build(result));

        ModuleSection moduleExplorer =
                new ModuleSection();

        html.append(moduleExplorer.build(result));

        DependencyHeatmapSection heatmapSection =
                new DependencyHeatmapSection();

        html.append(heatmapSection.build(result));

        GodClassSection godClassSection =
                new GodClassSection();

        html.append(godClassSection.build(analysis));

        DeadCodeSection deadCodeSection =
                new DeadCodeSection();

        html.append(deadCodeSection.build(analysis));

        html.append("""
<script>

function searchApi(){

    let input = document.getElementById("apiSearch");

    let filter = input.value.toUpperCase();

    let table = document.getElementById("apiTable");

    let tr = table.getElementsByTagName("tr");

    for(let i=1;i<tr.length;i++){

        let txt = tr[i].textContent;

        if(txt.toUpperCase().indexOf(filter)>-1)
            tr[i].style.display="";
        else
            tr[i].style.display="none";
    }

}

</script>
""");

        html.append("</body></html>");

        return html.toString();
    }

 }