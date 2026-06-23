package com.ia_image_generator.image_validate.dto;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageRequestDTO {
    private UUID requestId;
    private Long userId;
    private String prompt;
    private Instant timestamp;
}