package com.logsentinel.backend.controller;

import com.logsentinel.backend.entity.LogEntry;
import com.logsentinel.backend.service.LogEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
@CrossOrigin("*")
public class LogEntryController {

    private final LogEntryService logEntryService;

    @PostMapping
    public LogEntry saveLog(@RequestBody LogEntry logEntry) {
        return logEntryService.saveLog(logEntry);
    }

    @GetMapping
    public List<LogEntry> getAllLogs() {
        return logEntryService.getAllLogs();
    }

    @GetMapping("/severity/{severity}")
    public List<LogEntry> getLogsBySeverity(@PathVariable String severity) {
        return logEntryService.getLogsBySeverity(severity);
    }

    @GetMapping("/ip/{ipAddress}")
    public List<LogEntry> getLogsByIp(@PathVariable String ipAddress) {
        return logEntryService.getLogsByIp(ipAddress);
    }

    @GetMapping("/event/{eventType}")
    public List<LogEntry> getLogsByEventType(@PathVariable String eventType) {
        return logEntryService.getLogsByEventType(eventType);
    }

    @GetMapping("/search")
    public List<LogEntry> searchLogs(
            @RequestParam(required = false) String severity,
            @RequestParam(required = false) String eventType,
            @RequestParam(required = false) String ipAddress
    ) {

        if (severity != null && !severity.isBlank()) {
            return logEntryService.getLogsBySeverity(severity);
        }
        if (eventType != null && !eventType.isBlank()) {
            return logEntryService.getLogsByEventType(eventType);
        }
        if (ipAddress != null && !ipAddress.isBlank()) {
            return logEntryService.getLogsByIp(ipAddress);
        }
        return logEntryService.getAllLogs();
    }
}