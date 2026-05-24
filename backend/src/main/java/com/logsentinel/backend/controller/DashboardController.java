package com.logsentinel.backend.controller;

import com.logsentinel.backend.entity.Alert;
import com.logsentinel.backend.entity.LogEntry;

import com.logsentinel.backend.repository.AlertRepository;
import com.logsentinel.backend.repository.LogEntryRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@CrossOrigin("*")
public class DashboardController {

    private final LogEntryRepository logEntryRepository;
    private final AlertRepository alertRepository;

    @GetMapping("/metrics")
    public Map<String, Object> getMetrics() {

        List<LogEntry> logs = logEntryRepository.findAll();

        List<Alert> alerts = alertRepository.findAll();

        long criticalAlerts = logs.stream()
                .filter(log -> "CRITICAL".equals(log.getSeverity()))
                .count();

        String topAttackerIp = logs.stream()
                .collect(Collectors.groupingBy(
                        LogEntry::getIpAddress,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A");

        Map<String, Object> metrics = new HashMap<>();

        metrics.put("totalLogs", logs.size());
        metrics.put("totalAlerts", alerts.size());
        metrics.put("criticalAlerts", criticalAlerts);
        metrics.put("topAttackerIp", topAttackerIp);

        return metrics;
    }
}