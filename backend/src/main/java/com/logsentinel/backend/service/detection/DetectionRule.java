package com.logsentinel.backend.service.detection;

import com.logsentinel.backend.entity.LogEntry;

public interface DetectionRule {
    void evaluate(LogEntry logEntry);
}