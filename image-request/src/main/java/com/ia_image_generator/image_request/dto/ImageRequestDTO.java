package com.ia_image_generator.image_request.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.UUID;

public record ImageRequestDTO(
        UUID requestId,

        @NotNull(message = "userId é obrigatório")
        Long userId,

        @NotBlank(message = "prompt é obrigatório")
        String prompt,

        Instant timestamp
) {
}