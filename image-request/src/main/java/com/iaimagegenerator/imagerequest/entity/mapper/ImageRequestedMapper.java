package com.iaimagegenerator.imagerequest.entity.mapper;

import com.iaimagegenerator.imagerequest.entity.ImageRequested;
import com.iaimagegenerator.imagerequest.entity.dto.ImageRequestDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public final class ImageRequestedMapper {
    private ImageRequestedMapper() {
    }

    public static ImageRequested toEntity(ImageRequestDTO dto) {
        return ImageRequested.builder()
                .requestId(dto.requestId() != null ? dto.requestId() : UUID.randomUUID())
                .userId(dto.userId())
                .prompt(dto.prompt())
                .requestedAt(dto.timestamp() != null ? dto.timestamp() : LocalDateTime.now())
                .build();
    }

    public static ImageRequestDTO toDto(ImageRequested entity) {
        return new ImageRequestDTO(
                entity.getRequestId(),
                entity.getUserId(),
                entity.getPrompt(),
                entity.getRequestedAt()
        );
    }
}
