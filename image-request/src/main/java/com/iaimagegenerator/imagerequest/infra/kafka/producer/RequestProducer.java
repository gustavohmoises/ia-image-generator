package com.iaimagegenerator.imagerequest.infra.kafka.producer;

import com.iaimagegenerator.imagerequest.entity.dto.ImageRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class RequestProducer {
    @Autowired
    private KafkaTemplate<String, ImageRequestDTO> kafkaTemplate;

    public void sendRequest(ImageRequestDTO request) {
        kafkaTemplate.send("image-request", request);
    }

}
