package com.iaimagegenerator.imagegenerate.service;

import com.iaimagegenerator.imagegenerate.entity.dto.ImageRequestDTO;
import com.iaimagegenerator.imagegenerate.entity.ImageGenerated;
import com.iaimagegenerator.imagegenerate.entity.mapper.ImageGeneratedMapper;
import com.iaimagegenerator.imagegenerate.repository.ImageGeneratedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ImageGenerateService {
    @Autowired
    private ImageGeneratedRepository imageGeneratedRepository;

    public void process(ImageRequestDTO request) {

        ImageGenerated imageGenerated = ImageGeneratedMapper.toEntity(request);

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
