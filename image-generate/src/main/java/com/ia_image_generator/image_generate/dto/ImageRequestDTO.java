package com.ia_image_generator.image_generate.dto;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ImageRequestDTO {
    private UUID requestId;
    private Long userId;
    private String prompt;
    private Instant timestamp;
}