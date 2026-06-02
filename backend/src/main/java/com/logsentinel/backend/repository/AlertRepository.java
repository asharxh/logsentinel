package com.logsentinel.backend.repository;

import com.logsentinel.backend.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlertRepository extends JpaRepository<Alert, Long> {

    Optional<Alert> findTopByIpAddressAndAlertTypeOrderByCreatedAtDesc(
            String ipAddress,
            String alertType
    );

    long countBySeverity(String critical);
}
