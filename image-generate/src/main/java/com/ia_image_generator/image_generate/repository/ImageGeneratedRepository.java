package com.ia_image_generator.image_generate.repository;

import com.ia_image_generator.image_generate.entity.ImageGenerated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageGeneratedRepository extends JpaRepository<ImageGenerated, Long> {
}
