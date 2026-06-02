package com.logsentinel.backend.service;

import com.logsentinel.backend.dto.DashboardMetrics;
import com.logsentinel.backend.repository.AlertRepository;
import com.logsentinel.backend.repository.LogEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final LogEntryRepository logRepository;
    private final AlertRepository alertRepository;

    public DashboardMetrics getMetrics() {

        return DashboardMetrics.builder()
                .totalLogs(logRepository.count())
                .totalAlerts(alertRepository.count())
                .criticalAlerts(
                        alertRepository.countBySeverity("CRITICAL")
                )
                .topAttackerIp(
                        logRepository.findTopAttackerIp()
                )
                .build();
    }
}