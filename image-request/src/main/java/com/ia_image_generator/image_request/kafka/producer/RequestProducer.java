package com.ia_image_generator.image_request.kafka.producer;

import com.ia_image_generator.image_request.dto.ImageRequestDTO;
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
