package com.logsentinel.backend.service;

import com.logsentinel.backend.entity.Alert;
import com.logsentinel.backend.repository.AlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertService {

    private final AlertRepository alertRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public Alert saveAlert(Alert alert) {

        Alert savedAlert = alertRepository.save(alert);

        messagingTemplate.convertAndSend(
                "/topic/alerts",
                savedAlert
        );
        return savedAlert;
    }

    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }
}