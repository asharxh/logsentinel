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
}