package com.ia_image_generator.image_request.controller;

import com.ia_image_generator.image_request.dto.ImageRequestDTO;
import com.ia_image_generator.image_request.service.ImageRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/images")
public class ImageRequestController {

    @Autowired
    private ImageRequestService imageRequestService;

    @PostMapping
    public ResponseEntity<ImageRequestDTO> registerRequest(@RequestBody @Valid ImageRequestDTO dto) {
        ImageRequestDTO request = imageRequestService.registerRequest(dto);
        return ResponseEntity.accepted().body(request);
    }
}
