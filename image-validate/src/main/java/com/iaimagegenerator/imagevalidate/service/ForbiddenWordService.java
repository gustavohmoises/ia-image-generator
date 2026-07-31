package com.iaimagegenerator.imagevalidate.service;

import com.iaimagegenerator.imagevalidate.dto.ForbiddenWordCreateDTO;
import com.iaimagegenerator.imagevalidate.entity.ForbiddenWord;
import com.iaimagegenerator.imagevalidate.repository.ForbiddenWordRepository;
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
