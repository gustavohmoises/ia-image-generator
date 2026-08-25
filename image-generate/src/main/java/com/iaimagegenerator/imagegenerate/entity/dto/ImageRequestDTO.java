package com.iaimagegenerator.imagegenerate.entity.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record ImageRequestDTO(
    UUID requestId,
    Long userId,
    String prompt,
    LocalDateTime timestamp
) {
}