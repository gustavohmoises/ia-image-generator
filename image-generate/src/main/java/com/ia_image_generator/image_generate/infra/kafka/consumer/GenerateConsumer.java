package com.ia_image_generator.image_generate.infra.kafka.consumer;

import com.ia_image_generator.image_generate.dto.ImageRequestDTO;
import com.ia_image_generator.image_generate.service.ImageGenerateService;
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
