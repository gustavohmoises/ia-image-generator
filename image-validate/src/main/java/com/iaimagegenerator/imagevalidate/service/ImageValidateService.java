package com.iaimagegenerator.imagevalidate.service;

import com.iaimagegenerator.imagevalidate.dto.ImageRequestDTO;
import com.iaimagegenerator.imagevalidate.infra.kafka.producer.ValidateProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageValidateService {

    @Autowired
    private ValidateProducer validateProducer;

    @Autowired
    private ForbiddenWordService forbiddenWordService;

    private boolean isValid(String prompt) {
        if (prompt == null || prompt.isBlank()) {
            return false;
        }

        String normalizedPrompt = prompt.toLowerCase();

        return forbiddenWordService.getForbiddenWordsCacheble()
                .stream()
                .noneMatch(normalizedPrompt::contains);
    }

    public void process(ImageRequestDTO request) {

        boolean valid = this.isValid(request.prompt());

        if (valid) {
            validateProducer.sendApproved(request);

            System.out.printf(
                "[APPROVED] Request ID [%s] | User [%s] | Prompt [%s]%n",
                request.requestId(),
                request.userId(),
                request.prompt()
            );

            return;
        }

        validateProducer.sendToDlq(request);

        System.out.printf(
            "[REJECTED] Request ID [%s] | User [%s] | Prompt [%s]%n",
            request.requestId(),
            request.userId(),
            request.prompt()
        );
    }
}
