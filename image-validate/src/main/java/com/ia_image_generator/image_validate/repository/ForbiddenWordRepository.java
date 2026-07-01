package com.ia_image_generator.image_validate.repository;

import com.ia_image_generator.image_validate.entity.ForbiddenWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ForbiddenWordRepository extends JpaRepository<ForbiddenWord, Long> {
    Optional<ForbiddenWord> findByWord(String word);
}
