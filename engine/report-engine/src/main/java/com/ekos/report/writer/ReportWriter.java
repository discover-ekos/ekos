package com.ekos.report.writer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReportWriter {

    public void write(String html,
                      Path outputFile) throws IOException {

        Files.writeString(outputFile, html);

    }

}