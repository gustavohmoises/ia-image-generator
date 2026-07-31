package com.iaimagegenerator.imagerequest.repository;

import com.iaimagegenerator.imagerequest.entity.ImageRequested;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRequestedRepository extends JpaRepository<ImageRequested, Long> {
}
