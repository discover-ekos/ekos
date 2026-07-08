package com.ekos.backend.controller;

import com.ekos.backend.dto.ScanRequest;
import com.ekos.backend.dto.ScanResponse;
import com.ekos.backend.service.ScanService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/discovery")
public class ScanController {

    private final ScanService scanService;

    public ScanController(ScanService scanService) {
        this.scanService = scanService;
    }

    @PostMapping
    public ScanResponse scan(@RequestBody @Valid ScanRequest request) {
        return scanService.scan(request);
    }
}