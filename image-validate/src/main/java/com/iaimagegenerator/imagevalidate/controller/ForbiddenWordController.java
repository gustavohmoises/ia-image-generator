package com.iaimagegenerator.imagevalidate.controller;

import com.iaimagegenerator.imagevalidate.dto.ForbiddenWordCreateDTO;
import com.iaimagegenerator.imagevalidate.entity.ForbiddenWord;
import com.iaimagegenerator.imagevalidate.service.ForbiddenWordService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forbidden-words")
@SecurityRequirement(name = "bearerAuth")
public class ForbiddenWordController {

    @Autowired
    private ForbiddenWordService forbiddenWordService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ForbiddenWord> create(@RequestBody @Valid ForbiddenWordCreateDTO forbiddenWord) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(forbiddenWordService.create(forbiddenWord));
    }

    @GetMapping
    public ResponseEntity<List<ForbiddenWord>> findAll() {
        return ResponseEntity.ok(forbiddenWordService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        forbiddenWordService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
