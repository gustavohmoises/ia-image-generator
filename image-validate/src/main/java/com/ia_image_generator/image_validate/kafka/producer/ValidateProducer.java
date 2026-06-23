package com.ia_image_generator.image_validate.kafka.producer;

import com.ia_image_generator.image_validate.dto.ImageRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ValidateProducer {

    @Autowired
    private KafkaTemplate<String, ImageRequestDTO> kafkaTemplate;

    public void sendApproved(ImageRequestDTO dto) {
        kafkaTemplate.send("image-approved", dto);
    }

    public void sendToDlq(ImageRequestDTO dto) {
        kafkaTemplate.send("image-dlq", dto);
    }
}
