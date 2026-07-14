package com.ia_image_generator.image_generate.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "images_generated")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageGenerated {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "request_id", nullable = false, unique = true)
    private UUID requestId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String prompt;

    @Column(name = "requested_at", nullable = false)
    private Instant requestedAt;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;
}