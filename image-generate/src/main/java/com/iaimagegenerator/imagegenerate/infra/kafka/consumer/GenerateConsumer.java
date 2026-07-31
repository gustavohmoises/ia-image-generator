package com.iaimagegenerator.imagegenerate.infra.kafka.consumer;

import com.iaimagegenerator.imagegenerate.entity.dto.ImageRequestDTO;
import com.iaimagegenerator.imagegenerate.service.ImageGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class GenerateConsumer {
    @Autowired
    private ImageGenerateService imageGenerateService;

    @KafkaListener(topics = "image-approved", groupId = "generate-group")
    public void consume(ImageRequestDTO request) {
        imageGenerateService.process(request);
    }
}
