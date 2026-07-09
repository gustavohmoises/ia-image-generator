package com.ia_image_generator.image_validate.dto;

import jakarta.validation.constraints.NotBlank;

public record ForbiddenWordCreateDTO(@NotBlank String word) {
}
