package com.ia_image_generator.image_generate.repository;

import com.ia_image_generator.image_generate.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    boolean existsByRequestId(UUID requestId);

    Optional<Image> findByRequestId(UUID requestId);
}
