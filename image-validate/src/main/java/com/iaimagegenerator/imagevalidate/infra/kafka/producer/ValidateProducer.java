package com.iaimagegenerator.imagevalidate.infra.kafka.producer;

import com.iaimagegenerator.imagevalidate.dto.ImageRequestDTO;
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
