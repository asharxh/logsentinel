package com.logsentinel.backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardMetrics {

    private long totalLogs;
    private long totalAlerts;
    private long criticalAlerts;
    private String topAttackerIp;
}