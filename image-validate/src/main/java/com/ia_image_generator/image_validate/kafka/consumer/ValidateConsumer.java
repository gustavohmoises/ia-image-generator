package com.ia_image_generator.image_validate.kafka.consumer;

import com.ia_image_generator.image_validate.dto.ImageRequestDTO;
import com.ia_image_generator.image_validate.service.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ValidateConsumer {

    @Autowired
    private ValidateService validateService;

    @KafkaListener(topics = "image-request", groupId = "validation-group")
    public void consume(ImageRequestDTO request) {
        validateService.process(request);
    }
}