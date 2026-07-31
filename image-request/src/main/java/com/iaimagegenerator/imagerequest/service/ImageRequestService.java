package com.iaimagegenerator.imagerequest.service;

import com.iaimagegenerator.imagerequest.entity.dto.ImageRequestDTO;
import com.iaimagegenerator.imagerequest.entity.ImageRequested;
import com.iaimagegenerator.imagerequest.entity.mapper.ImageRequestedMapper;
import com.iaimagegenerator.imagerequest.infra.kafka.producer.RequestProducer;
import com.iaimagegenerator.imagerequest.repository.ImageRequestedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageRequestService {
    @Autowired
    private RequestProducer requestProducer;

    @Autowired
    private ImageRequestedRepository imageRequestedRepository;

    public ImageRequestDTO registerRequest(ImageRequestDTO dto) {
        ImageRequested entity = ImageRequestedMapper.toEntity(dto);
        imageRequestedRepository.save(entity);

        ImageRequestDTO request = ImageRequestedMapper.toDto(entity);
        requestProducer.sendRequest(request);

        return request;
    }
}
