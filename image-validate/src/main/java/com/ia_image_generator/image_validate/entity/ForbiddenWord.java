package com.ia_image_generator.image_validate.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "forbidden_words")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ForbiddenWord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "word é obrigatório")
    @Column(nullable = false, unique = true)
    private String word;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = OffsetDateTime.now();
    }
}
