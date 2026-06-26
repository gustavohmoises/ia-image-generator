package com.ia_image_generator.image_generate.service;

import com.ia_image_generator.image_generate.dto.ImageRequestDTO;
import com.ia_image_generator.image_generate.entity.Image;
import com.ia_image_generator.image_generate.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GenerateService {

    @Autowired
    private ImageRepository imageRepository;

    public void process(ImageRequestDTO request) {

        Image image = Image.builder()
                .requestId(request.getRequestId())
                .userId(request.getUserId())
                .prompt(request.getPrompt())
                .build();

        try {
            imageRepository.save(image);
        } catch (DataIntegrityViolationException ex) {
            System.out.printf(
                    "[INFO] Imagem ID [%s] já foi processada.%n",
                    request.getRequestId()
            );

            return;
        }

        System.out.printf(
                "[INFO] Imagem ID [%s] solicitada pelo usuário [%s] gerada com sucesso: [%s]%n",
                request.getRequestId(),
                request.getUserId(),
                request.getPrompt()
        );
    }
}
