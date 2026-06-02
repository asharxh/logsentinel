package com.logsentinel.backend.controller;

import com.logsentinel.backend.dto.DashboardMetrics;
import com.logsentinel.backend.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/metrics")
    public DashboardMetrics metrics() {
        return dashboardService.getMetrics();
    }
}