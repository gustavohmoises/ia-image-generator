package com.iaimagegenerator.imagegenerate.service;

import com.iaimagegenerator.imagegenerate.entity.dto.ImageRequestDTO;
import com.iaimagegenerator.imagegenerate.entity.ImageGenerated;
import com.iaimagegenerator.imagegenerate.repository.ImageGeneratedRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ImageGenerateServiceTest {

    @InjectMocks
    private ImageGenerateService service;

    @Mock
    private ImageGeneratedRepository imageGeneratedRepository;

    @Test
    void shouldSaveImage() {
        // Cria um DTO simulando uma requisição de geração de imagem aprovada
        ImageRequestDTO dto = new ImageRequestDTO(
                UUID.randomUUID(),
                1L,
                "imagem feliz",
                LocalDateTime.now()
        );

        // Simula o salvamento da entidade no banco de dados
        when(imageGeneratedRepository.save(any(ImageGenerated.class)))
                .thenReturn(new ImageGenerated());

        // Executa o processamento da geração da imagem
        service.process(dto);

        // Verifica se a imagem gerada foi salva no repositório
        verify(imageGeneratedRepository).save(any(ImageGenerated.class));
    }

    @Test
    void shouldIgnoreDuplicateImage() {

        // Cria um DTO simulando uma requisição de geração de imagem duplicada
        ImageRequestDTO dto = new ImageRequestDTO(
                UUID.randomUUID(),
                1L,
                "imagem feliz",
                LocalDateTime.now()
        );

        // Simula uma exceção do banco de dados ao tentar salvar uma imagem duplicada
        when(imageGeneratedRepository.save(any(ImageGenerated.class)))
                .thenThrow(DataIntegrityViolationException.class);

        // Executa o processamento da geração da imagem
        // O serviço deve tratar a exceção e evitar que ela seja propagada
        service.process(dto);

        // Verifica se a tentativa de salvar a imagem foi realizada
        verify(imageGeneratedRepository).save(any(ImageGenerated.class));
    }
}