package com.logsentinel.backend.service;

import com.logsentinel.backend.entity.LogEntry;
import com.logsentinel.backend.repository.LogEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogEntryService {

    private final LogEntryRepository logEntryRepository;

    public LogEntry saveLog(LogEntry logEntry) {
        return logEntryRepository.save(logEntry);
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