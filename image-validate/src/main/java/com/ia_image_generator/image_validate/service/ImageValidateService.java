package com.ia_image_generator.image_validate.service;

import com.ia_image_generator.image_validate.dto.ImageRequestDTO;
import com.ia_image_generator.image_validate.infra.kafka.producer.ValidateProducer;
import com.ia_image_generator.image_validate.repository.ForbiddenWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
