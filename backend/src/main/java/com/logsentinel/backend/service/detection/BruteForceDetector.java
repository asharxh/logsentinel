package com.logsentinel.backend.service.detection;

import com.logsentinel.backend.entity.Alert;
import com.logsentinel.backend.entity.LogEntry;
import com.logsentinel.backend.repository.AlertRepository;
import com.logsentinel.backend.repository.LogEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BruteForceDetector implements DetectionRule {

    private final LogEntryRepository logEntryRepository;
    private final AlertRepository alertRepository;

    @Override
    public void evaluate(LogEntry logEntry) {

        if (!"FAILED_LOGIN".equals(logEntry.getEventType())) {
            return;
        }

        long failedCount =
                logEntryRepository.countByIpAddressAndEventType(
                        logEntry.getIpAddress(),
                        "FAILED_LOGIN"
                );

        if (failedCount < 5) {
            return;
        }

        var existingAlert =
                alertRepository
                        .findTopByIpAddressAndAlertTypeOrderByCreatedAtDesc(
                                logEntry.getIpAddress(),
                                "BRUTE_FORCE_ATTACK"
                        );

        if (existingAlert.isPresent()) {

            LocalDateTime lastAlertTime =
                    existingAlert.get().getCreatedAt();

            if (lastAlertTime.isAfter(
                    LocalDateTime.now().minusMinutes(2)
            )) {
                return;
            }
        }

        Alert alert = Alert.builder()
                .alertType("BRUTE_FORCE_ATTACK")
                .severity("HIGH")
                .ipAddress(logEntry.getIpAddress())
                .message("Multiple failed login attempts detected")
                .createdAt(LocalDateTime.now())
                .build();

        alertRepository.save(alert);
    }
}