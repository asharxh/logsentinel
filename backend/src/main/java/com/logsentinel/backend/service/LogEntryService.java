package com.logsentinel.backend.service;

import com.logsentinel.backend.entity.Alert;
import com.logsentinel.backend.entity.LogEntry;
import com.logsentinel.backend.repository.AlertRepository;
import com.logsentinel.backend.repository.LogEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LogEntryService {

    private final LogEntryRepository logEntryRepository;
    private final AlertRepository alertRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public LogEntry saveLog(LogEntry logEntry) {

        LogEntry savedLog = logEntryRepository.save(logEntry);

        messagingTemplate.convertAndSend(
                "/topic/logs",
                savedLog
        );

        detectBruteForce(savedLog);

        return savedLog;
    }

    private void detectBruteForce(LogEntry logEntry) {

        if (!"FAILED_LOGIN".equals(logEntry.getEventType())) {
            return;
        }

        long failedCount =
                logEntryRepository.countByIpAddressAndEventType(
                        logEntry.getIpAddress(),
                        "FAILED_LOGIN"
                );

        if (failedCount >= 5) {

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

    public List<LogEntry> getAllLogs() {
        return logEntryRepository.findAllByOrderByTimestampDesc();
    }

    public List<LogEntry> getLogsBySeverity(String severity) {
        return logEntryRepository.findBySeverity(severity);
    }

    public List<LogEntry> getLogsByIp(String ipAddress) {
        return logEntryRepository.findByIpAddress(ipAddress);
    }

    public List<LogEntry> getLogsByEventType(String eventType) {
        return logEntryRepository.findByEventType(eventType);
    }
}