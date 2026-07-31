package com.iaimagegenerator.imagevalidate.service;

import com.iaimagegenerator.imagevalidate.dto.ImageRequestDTO;
import com.iaimagegenerator.imagevalidate.infra.kafka.producer.ValidateProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImageValidateServiceTest {

    @Mock
    private ValidateProducer validateProducer;

    @Mock
    private ForbiddenWordService forbiddenWordService;

    @InjectMocks
    private ImageValidateService imageValidateService;

    @Test
    void shouldApproveValidPrompt() {

        // Cria um DTO contendo um prompt sem palavras proibidas
        ImageRequestDTO dto = new ImageRequestDTO(
                UUID.randomUUID(),
                1L,
                "imagem feliz",
                LocalDateTime.now()
        );

        // Simula o retorno das palavras proibidas cadastradas
        when(forbiddenWordService.getForbiddenWordsCacheble())
                .thenReturn(List.of("drogas", "cachaca"));

        // Executa o processamento da requisição
        imageValidateService.process(dto);

        // Verifica se a mensagem foi enviada para o tópico de aprovados
        verify(validateProducer).sendApproved(dto);

        // Verifica que nenhuma mensagem foi enviada para a fila de rejeitados (DLQ)
        verify(validateProducer, never()).sendToDlq(any());
    }

    @Test
    void shouldRejectForbiddenPrompt() {

        // Cria um DTO contendo um prompt com uma palavra proibida
        ImageRequestDTO dto = new ImageRequestDTO(
                UUID.randomUUID(),
                1L,
                "imagem cachaca",
                LocalDateTime.now()
        );

        // Simula o retorno da lista de palavras proibidas
        when(forbiddenWordService.getForbiddenWordsCacheble())
                .thenReturn(List.of("cachaca"));

        // Executa o processamento da requisição
        imageValidateService.process(dto);

        // Verifica se a mensagem foi enviada para a fila de rejeitados (DLQ)
        verify(validateProducer).sendToDlq(dto);

        // Verifica que nenhuma mensagem foi enviada para o tópico de aprovados
        verify(validateProducer, never()).sendApproved(any());
    }
}