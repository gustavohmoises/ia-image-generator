package com.ia_image_generator.image_request.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "images_requested")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageRequested {
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
}
