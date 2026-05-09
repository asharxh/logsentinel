package com.logsentinel.backend.repository;

import com.logsentinel.backend.entity.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogEntryRepository extends JpaRepository<LogEntry, Long> {
}