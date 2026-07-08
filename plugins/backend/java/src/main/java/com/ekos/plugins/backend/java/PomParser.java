package com.ekos.plugins.backend.java;

import com.ekos.discovery.model.ProjectMetadata;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class PomParser {

    public ProjectMetadata parse(File pomFile) {

        ProjectMetadata metadata = new ProjectMetadata();

        try {

            Document document = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(pomFile);

            document.getDocumentElement().normalize();

            metadata.setGroupId(getValue(document, "groupId"));
            metadata.setArtifactId(getValue(document, "artifactId"));
            metadata.setVersion(getValue(document, "version"));
            metadata.setPackaging(getValue(document, "packaging"));

        } catch (Exception e) {
            throw new RuntimeException("Unable to parse pom.xml", e);
        }

        return metadata;
    }

    private String getValue(Document document, String tagName) {

        NodeList nodes = document.getElementsByTagName(tagName);

        if (nodes.getLength() == 0) {
            return null;
        }

        return nodes.item(0).getTextContent();
    }
}