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

    public ImageRequestDTO registerRequest(ImageRequestDTO requestDTO) {
        ImageRequested entity = ImageRequestedMapper.toEntity(requestDTO);
        imageRequestedRepository.save(entity);

        ImageRequestDTO responseDto = ImageRequestedMapper.toDto(entity);
        requestProducer.sendRequest(responseDto);

        return responseDto;
    }
}
