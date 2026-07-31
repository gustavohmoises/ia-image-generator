package com.iaimagegenerator.imagegenerate.entity.mapper;

import com.iaimagegenerator.imagegenerate.entity.ImageGenerated;
import com.iaimagegenerator.imagegenerate.entity.dto.ImageRequestDTO;

import java.time.LocalDateTime;

// TODO - implementar testes unitários
public final class ImageGeneratedMapper {
    private ImageGeneratedMapper() {
    }

    public static ImageGenerated toEntity (ImageRequestDTO dto) {
        return ImageGenerated.builder()
                .requestId(dto.requestId())
                .userId(dto.userId())
                .prompt(dto.prompt())
                .requestedAt(dto.timestamp())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static ImageRequestDTO toDto (ImageGenerated entity) {
        return new ImageRequestDTO(
                entity.getRequestId(),
                entity.getUserId(),
                entity.getPrompt(),
                entity.getRequestedAt()
        );
    }
}
