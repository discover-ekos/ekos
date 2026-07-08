package com.ekos.backend.controller;

import com.ekos.backend.service.ScanService;
import com.ekos.discovery.model.DiscoveryResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiscoveryController {

    private final ScanService scanService;

    public DiscoveryController(ScanService scanService) {
        this.scanService = scanService;
    }

    @GetMapping("/scan")
    public DiscoveryResult scan(@RequestParam String path) {

        return scanService.scan(path);

    }

}