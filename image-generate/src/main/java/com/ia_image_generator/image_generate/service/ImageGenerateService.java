package com.ia_image_generator.image_generate.service;

import com.ia_image_generator.image_generate.dto.ImageRequestDTO;
import com.ia_image_generator.image_generate.entity.ImageGenerated;
import com.ia_image_generator.image_generate.repository.ImageGeneratedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ImageGenerateService {
    @Autowired
    private ImageGeneratedRepository imageGeneratedRepository;

    public void process(ImageRequestDTO request) {

        ImageGenerated imageGenerated = new ImageGenerated();
        imageGenerated.setRequestId(request.requestId());
        imageGenerated.setUserId(request.userId());
        imageGenerated.setPrompt(request.prompt());
        imageGenerated.setRequestedAt(request.timestamp());
        imageGenerated.setCreatedAt(Instant.now());

        try {
            imageGeneratedRepository.save(imageGenerated);
        } catch (DataIntegrityViolationException ex) {
            System.out.printf(
                    "[INFO] Imagem ID [%s] já foi processada.%n",
                    request.requestId()
            );

            return;
        }

        System.out.printf(
                "[INFO] Imagem ID [%s] solicitada pelo usuário [%s] gerada com sucesso: [%s]%n",
                request.requestId(),
                request.userId(),
                request.prompt()
        );
    }
}
