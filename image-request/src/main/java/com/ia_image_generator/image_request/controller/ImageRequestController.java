package com.ia_image_generator.image_request.controller;

import com.ia_image_generator.image_request.dto.ImageRequestDTO;
import com.ia_image_generator.image_request.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/images")
public class ImageRequestController {

    @Autowired
    private RequestService requestService;

    @PostMapping
    public ResponseEntity<ImageRequestDTO> registerRequest(@RequestBody @Valid ImageRequestDTO dto) {
        ImageRequestDTO request = requestService.registerRequest(dto);
        return ResponseEntity.accepted().body(request);
    }
}
