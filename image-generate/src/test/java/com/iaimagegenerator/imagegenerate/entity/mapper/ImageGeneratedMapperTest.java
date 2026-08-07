package com.iaimagegenerator.imagegenerate.entity.mapper;

import com.iaimagegenerator.imagegenerate.entity.ImageGenerated;
import com.iaimagegenerator.imagegenerate.entity.dto.ImageRequestDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ImageGeneratedMapperTest {

    @Test
    void shouldMapDtoToEntity() {
        UUID requestId = UUID.randomUUID();
        LocalDateTime timestamp = LocalDateTime.now();

        ImageRequestDTO dto = new ImageRequestDTO(
                requestId,
                1L,
                "Imagem cachorro",
                timestamp
        );

        ImageGenerated entity = ImageGeneratedMapper.toEntity(dto);

        assertEquals(requestId, entity.getRequestId());
        assertEquals(1L, entity.getUserId());
        assertEquals("Imagem cachorro", entity.getPrompt());
        assertEquals(timestamp, entity.getRequestedAt());
        assertNotNull(entity.getCreatedAt());
    }

    @Test
    void shouldMapEntityToDto() {
        UUID requestId = UUID.randomUUID();
        LocalDateTime timestamp = LocalDateTime.now();

        ImageGenerated entity = ImageGenerated.builder()
                .requestId(requestId)
                .userId(1L)
                .prompt("Imagem gato")
                .requestedAt(timestamp)
                .createdAt(LocalDateTime.now())
                .build();

        ImageRequestDTO dto = ImageGeneratedMapper.toDto(entity);

        assertEquals(requestId, dto.requestId());
        assertEquals(1L, dto.userId());
        assertEquals("Imagem gato", dto.prompt());
        assertEquals(timestamp, dto.timestamp());
    }
}