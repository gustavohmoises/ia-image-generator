package com.iaimagegenerator.imagevalidate.dto;

import com.iaimagegenerator.imagevalidate.entity.UserRole;

public record RegisterDTO (
        String login,
        String password,
        UserRole role
) {}

