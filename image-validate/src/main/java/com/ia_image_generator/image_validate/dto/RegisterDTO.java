package com.ia_image_generator.image_validate.dto;

import com.ia_image_generator.image_validate.entity.UserRole;

public record RegisterDTO (
        String login,
        String password,
        UserRole role
) {}

