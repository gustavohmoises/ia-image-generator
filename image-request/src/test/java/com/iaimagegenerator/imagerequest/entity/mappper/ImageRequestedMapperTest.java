package com.iaimagegenerator.imagerequest.entity.mappper;

import com.iaimagegenerator.imagerequest.entity.ImageRequested;
import com.iaimagegenerator.imagerequest.entity.dto.ImageRequestDTO;
import com.iaimagegenerator.imagerequest.entity.mapper.ImageRequestedMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ImageRequestedMapperTest {
    @Test
    void shouldConvertDtoToEntity() {
        UUID requestId = UUID.randomUUID();
        LocalDateTime timestamp = LocalDateTime.now();

        ImageRequestDTO dto = new ImageRequestDTO(
                requestId,
                1L,
                "Imagem fazenda",
                timestamp
        );

        ImageRequested entity = ImageRequestedMapper.toEntity(dto);

        assertEquals(requestId, entity.getRequestId());
        assertEquals(1L, entity.getUserId());
        assertEquals("Imagem fazenda", entity.getPrompt());
        assertEquals(timestamp, entity.getRequestedAt());
    }

    @Test
    void shouldGenerateRequestIdWhenRequestIdIsNull() {
        ImageRequestDTO dto = new ImageRequestDTO(
                null,
                1L,
                "Imagem fazenda",
                LocalDateTime.now()
        );

        ImageRequested entity = ImageRequestedMapper.toEntity(dto);

        assertNotNull(entity.getRequestId());
    }

    @Test
    void shouldGenerateTimestampWhenTimestampIsNull() {
        ImageRequestDTO dto = new ImageRequestDTO(
                UUID.randomUUID(),
                1L,
                "Imagem cachaca",
                null
        );

        ImageRequested entity = ImageRequestedMapper.toEntity(dto);
        assertNotNull(entity.getRequestedAt());
    }

    @Test
    void shouldConvertEntityToDto() {
        UUID requestId = UUID.randomUUID();
        LocalDateTime timestamp = LocalDateTime.now();

        ImageRequested entity = ImageRequested.builder()
                .requestId(requestId)
                .userId(5L)
                .prompt("Imagem computador")
                .requestedAt(timestamp)
                .build();


        ImageRequestDTO dto = ImageRequestedMapper.toDto(entity);

        assertEquals(requestId, dto.requestId());
        assertEquals(5L, dto.userId());
        assertEquals("Imagem computador", dto.prompt());
        assertEquals(timestamp, dto.timestamp());
    }
}
