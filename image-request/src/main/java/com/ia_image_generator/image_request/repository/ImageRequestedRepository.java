package com.ia_image_generator.image_request.repository;

import com.ia_image_generator.image_request.entity.ImageRequested;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRequestedRepository extends JpaRepository<ImageRequested, Long> {
}
