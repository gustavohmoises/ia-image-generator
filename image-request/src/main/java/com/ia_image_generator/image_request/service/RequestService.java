package com.ia_image_generator.image_request.service;

import com.ia_image_generator.image_request.dto.ImageRequestDTO;
import com.ia_image_generator.image_request.kafka.producer.RequestProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RequestService {
    @Autowired
    private RequestProducer requestProducer;

    public ImageRequestDTO registerRequest(ImageRequestDTO dto) {
        ImageRequestDTO request = new ImageRequestDTO();

        request.setRequestId(UUID.randomUUID());
        request.setUserId(dto.getUserId());
        request.setPrompt(dto.getPrompt());
        request.setTimestamp(Instant.now());

        requestProducer.sendRequest(request);

        return request;
    }
}
