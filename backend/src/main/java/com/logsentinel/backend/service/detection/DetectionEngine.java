package com.logsentinel.backend.service.detection;

import com.logsentinel.backend.entity.LogEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetectionEngine {

    private final List<DetectionRule> rules;

    public void analyze(LogEntry logEntry) {

        for (DetectionRule rule : rules) {
            rule.evaluate(logEntry);
        }
    }
}