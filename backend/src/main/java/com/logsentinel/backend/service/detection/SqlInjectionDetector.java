package com.logsentinel.backend.service.detection;

import com.logsentinel.backend.entity.Alert;
import com.logsentinel.backend.entity.LogEntry;
import com.logsentinel.backend.repository.AlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SqlInjectionDetector implements DetectionRule {

    private final AlertRepository alertRepository;

    @Override
    public void evaluate(LogEntry logEntry) {

        if (!"SQL_INJECTION".equals(logEntry.getEventType())) {
            return;
        }

        Alert alert = Alert.builder()
                .alertType("SQL_INJECTION_ATTACK")
                .severity("CRITICAL")
                .ipAddress(logEntry.getIpAddress())
                .message("Possible SQL Injection detected")
                .createdAt(LocalDateTime.now())
                .build();

        alertRepository.save(alert);
    }
}