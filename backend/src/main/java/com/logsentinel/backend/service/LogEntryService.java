package com.logsentinel.backend.service;

import com.logsentinel.backend.entity.Alert;
import com.logsentinel.backend.entity.LogEntry;
import com.logsentinel.backend.repository.AlertRepository;
import com.logsentinel.backend.repository.LogEntryRepository;
import com.logsentinel.backend.service.detection.DetectionEngine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LogEntryService {

    private final LogEntryRepository logEntryRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final DetectionEngine detectionEngine;

    public LogEntry saveLog(LogEntry logEntry) {

        LogEntry savedLog =
                logEntryRepository.save(logEntry);

        messagingTemplate.convertAndSend(
                "/topic/logs",
                savedLog
        );

        detectionEngine.analyze(savedLog);

        return savedLog;
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