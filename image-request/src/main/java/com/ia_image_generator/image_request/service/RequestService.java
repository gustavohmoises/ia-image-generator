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

        UUID requestId = dto.getRequestId() != null
                ? dto.getRequestId()
                : UUID.randomUUID();

        Instant timestamp = dto.getTimestamp() != null
                ? dto.getTimestamp()
                : Instant.now();

        request.setRequestId(requestId);
        request.setTimestamp(timestamp);
        request.setUserId(dto.getUserId());
        request.setPrompt(dto.getPrompt());

        requestProducer.sendRequest(request);

        return request;
    }
}
