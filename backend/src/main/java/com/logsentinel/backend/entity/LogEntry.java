package com.logsentinel.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;

    private String severity;

    private String source;

    private String ipAddress;

    private String eventType;

    @Column(columnDefinition = "TEXT")
    private String message;
}