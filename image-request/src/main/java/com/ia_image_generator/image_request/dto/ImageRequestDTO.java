package com.ia_image_generator.image_request.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageRequestDTO {
    private UUID requestId;

    @NotNull(message = "userId é obrigatório")
    private Long userId;

    @NotBlank(message = "prompt é obrigatório")
    private String prompt;

    private Instant timestamp;
}