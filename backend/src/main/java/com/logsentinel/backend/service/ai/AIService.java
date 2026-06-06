/*
package com.logsentinel.backend.service.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AIService {

    private final ChatClient chatClient;

    public AIService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String explainAlert(
            String alertType,
            String severity,
            String ipAddress,
            String message
    ) {

        String prompt = """
                You are a SOC analyst.

                Explain this security alert.

                Alert Type: %s
                Severity: %s
                IP Address: %s
                Message: %s

                Give:
                1. Explanation
                2. Risk
                3. Recommended Actions

                Keep response under 150 words.
                """
                .formatted(
                        alertType,
                        severity,
                        ipAddress,
                        message
                );

        return chatClient
                .prompt(prompt)
                .call()
                .content();
    }
}

 */