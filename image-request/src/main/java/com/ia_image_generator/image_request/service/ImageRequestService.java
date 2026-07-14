package com.ia_image_generator.image_request.service;

import com.ia_image_generator.image_request.dto.ImageRequestDTO;
import com.ia_image_generator.image_request.entity.ImageRequested;
import com.ia_image_generator.image_request.infra.kafka.producer.RequestProducer;
import com.ia_image_generator.image_request.repository.ImageRequestedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class ImageRequestService {
    @Autowired
    private RequestProducer requestProducer;

    @Autowired
    private ImageRequestedRepository imageRequestedRepository;

    public ImageRequestDTO registerRequest(ImageRequestDTO dto) {
        UUID requestId = dto.requestId() != null
                ? dto.requestId()
                : UUID.randomUUID();

        Instant requestedAt = dto.timestamp() != null
                ? dto.timestamp()
                : Instant.now();

        ImageRequested imageRequested = new ImageRequested();
        imageRequested.setRequestId(requestId);
        imageRequested.setUserId(dto.userId());
        imageRequested.setPrompt(dto.prompt());
        imageRequested.setRequestedAt(requestedAt);
        imageRequestedRepository.save(imageRequested);

        ImageRequestDTO request = new ImageRequestDTO(
            requestId,
            dto.userId(),
            dto.prompt(),
            requestedAt
        );

        requestProducer.sendRequest(request);

        return dto;
    }
}
