package com.ia_image_generator.image_validate.dto;

import java.time.Instant;
import java.util.UUID;

public record ImageRequestDTO(
        UUID requestId,
        Long userId,
        String prompt,
        Instant timestamp
) {}