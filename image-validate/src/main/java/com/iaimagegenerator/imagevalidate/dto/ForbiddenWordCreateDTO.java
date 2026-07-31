package com.iaimagegenerator.imagevalidate.dto;

import jakarta.validation.constraints.NotBlank;

public record ForbiddenWordCreateDTO(@NotBlank String word) {
}
