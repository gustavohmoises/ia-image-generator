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
    private ForbiddenWordRepository forbiddenWordRepository;

    private boolean isValid(String prompt) {
        if (prompt == null || prompt.isBlank()) {
            return false;
        }

        String normalizedPrompt = prompt.toLowerCase();

        List<String> forbiddenWords = forbiddenWordRepository
                .findAll()
                .stream()
                .map(word -> word.getWord().toLowerCase())
                .toList();

        return forbiddenWords.stream().noneMatch(normalizedPrompt::contains);
    }

    public void process(ImageRequestDTO request) {

        boolean valid = this.isValid(request.getPrompt());

        if (valid) {
            validateProducer.sendApproved(request);

            System.out.printf(
                "[APPROVED] Request ID [%s] | User [%s] | Prompt [%s]%n",
                request.getRequestId(),
                request.getUserId(),
                request.getPrompt()
            );

            return;
        }

        validateProducer.sendToDlq(request);

        System.out.printf(
            "[REJECTED] Request ID [%s] | User [%s] | Prompt [%s]%n",
            request.getRequestId(),
            request.getUserId(),
            request.getPrompt()
        );
    }
}
