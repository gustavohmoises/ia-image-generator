package com.ia_image_generator.image_validate.controller;

import com.ia_image_generator.image_validate.entity.ForbiddenWord;
import com.ia_image_generator.image_validate.service.ForbiddenWordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forbidden-words")
public class ForbiddenWordController {

    @Autowired
    private ForbiddenWordService forbiddenWordService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ForbiddenWord create(@RequestBody @Valid ForbiddenWord forbiddenWord) {
        return forbiddenWordService.create(forbiddenWord);
    }

    @GetMapping
    public List<ForbiddenWord> findAll() {
        return forbiddenWordService.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        forbiddenWordService.delete(id);
    }
}
