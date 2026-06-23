package com.ia_image_generator.image_generate.service;

import com.ia_image_generator.image_generate.dto.ImageRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class GenerateService {
    public void process(ImageRequestDTO request) {

        System.out.printf(
                "[INFO] Imagem ID [%s] solicitada pelo usuário [%s] gerada com sucesso: [%s]%n",
                request.getRequestId(),
                request.getUserId(),
                request.getPrompt()
        );
    }
}
