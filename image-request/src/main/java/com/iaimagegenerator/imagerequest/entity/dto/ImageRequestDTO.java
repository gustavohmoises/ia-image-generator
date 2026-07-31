package com.iaimagegenerator.imagerequest.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record ImageRequestDTO(
        UUID requestId,

        @NotNull(message = "userId é obrigatório")
        Long userId,

        @NotBlank(message = "prompt é obrigatório")
        String prompt,

        LocalDateTime timestamp
) {
}