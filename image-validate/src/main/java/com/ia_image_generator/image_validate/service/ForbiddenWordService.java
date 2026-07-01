package com.ia_image_generator.image_validate.service;

import com.ia_image_generator.image_validate.entity.ForbiddenWord;
import com.ia_image_generator.image_validate.repository.ForbiddenWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForbiddenWordService {

    @Autowired
    private ForbiddenWordRepository forbiddenWordRepository;

    public ForbiddenWord create(ForbiddenWord forbiddenWord) {
        forbiddenWordRepository.findByWord(forbiddenWord.getWord())
                .ifPresent(word -> {
                    throw new IllegalArgumentException(
                            "Palavra já cadastrada."
                    );
                });

        ForbiddenWord entity = ForbiddenWord.builder()
                .word(forbiddenWord.getWord().trim().toLowerCase())
                .build();

        return forbiddenWordRepository.save(entity);
    }

    public List<ForbiddenWord> findAll() {
        return forbiddenWordRepository.findAll();
    }

    public void delete(Long id) {
        if (!forbiddenWordRepository.existsById(id)) {
            throw new IllegalArgumentException(
                    "Palavra não encontrada."
            );
        }
        forbiddenWordRepository.deleteById(id);
    }
}
