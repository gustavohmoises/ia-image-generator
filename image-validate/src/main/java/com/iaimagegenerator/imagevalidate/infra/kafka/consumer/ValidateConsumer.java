package com.iaimagegenerator.imagevalidate.infra.kafka.consumer;

import com.iaimagegenerator.imagevalidate.dto.ImageRequestDTO;
import com.iaimagegenerator.imagevalidate.service.ImageValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ValidateConsumer {

    @Autowired
    private ImageValidateService imageValidateService;

    @KafkaListener(topics = "image-request", groupId = "validation-group")
    public void consume(ImageRequestDTO request) {
        imageValidateService.process(request);
    }
}