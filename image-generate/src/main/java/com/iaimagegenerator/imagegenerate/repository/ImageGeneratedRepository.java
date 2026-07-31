package com.iaimagegenerator.imagegenerate.repository;

import com.iaimagegenerator.imagegenerate.entity.ImageGenerated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageGeneratedRepository extends JpaRepository<ImageGenerated, Long> {
}
