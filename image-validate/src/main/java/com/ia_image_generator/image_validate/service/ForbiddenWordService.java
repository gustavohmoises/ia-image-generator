package com.ia_image_generator.image_validate.service;

import com.ia_image_generator.image_validate.dto.ForbiddenWordCreateDTO;
import com.ia_image_generator.image_validate.entity.ForbiddenWord;
import com.ia_image_generator.image_validate.repository.ForbiddenWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForbiddenWordService {

    @Autowired
    private ForbiddenWordRepository forbiddenWordRepository;

    @CacheEvict(value = "forbiddenWords", allEntries = true)
    public ForbiddenWord create(ForbiddenWordCreateDTO forbiddenWord) {
        forbiddenWordRepository.findByWord(forbiddenWord.word())
                .ifPresent(word -> {
                    throw new IllegalArgumentException(
                            "Palavra já cadastrada."
                    );
                });

        ForbiddenWord entity = ForbiddenWord.builder()
                .word(forbiddenWord.word().trim().toLowerCase())
                .build();

        return forbiddenWordRepository.save(entity);
    }

    public List<ForbiddenWord> findAll() {
        return forbiddenWordRepository.findAll();
    }

    @Cacheable("forbiddenWords")
    public List<String> getForbiddenWordsCacheble() {
        System.out.println("Buscando no banco...");
        return forbiddenWordRepository.findAll()
                .stream()
                .map(word -> word.getWord().toLowerCase())
                .toList();
    }

    @CacheEvict(value = "forbiddenWords", allEntries = true)
    public void delete(Long id) {
        if (!forbiddenWordRepository.existsById(id)) {
            throw new IllegalArgumentException(
                    "Palavra não encontrada."
            );
        }
        forbiddenWordRepository.deleteById(id);
    }
}
