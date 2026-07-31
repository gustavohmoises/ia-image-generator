package com.iaimagegenerator.imagegenerate.entity.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ImageRequestDTO(
    UUID requestId,
    Long userId,
    String prompt,
    LocalDateTime timestamp
) {
}