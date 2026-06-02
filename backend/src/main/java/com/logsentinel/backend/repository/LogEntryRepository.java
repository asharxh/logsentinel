package com.logsentinel.backend.repository;

import com.logsentinel.backend.entity.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LogEntryRepository extends JpaRepository<LogEntry, Long> {

    List<LogEntry> findBySeverity(String severity);

    List<LogEntry> findByIpAddress(String ipAddress);

    List<LogEntry> findByEventType(String eventType);

    List<LogEntry> findAllByOrderByTimestampDesc();

    long countByIpAddressAndEventType(String ipAddress, String eventType);
    long count();

    @Query("""
SELECT l.ipAddress
FROM LogEntry l
GROUP BY l.ipAddress
ORDER BY COUNT(l) DESC
LIMIT 1
""")
    String findTopAttackerIp();
}