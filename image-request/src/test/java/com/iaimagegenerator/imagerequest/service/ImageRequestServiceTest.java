package com.iaimagegenerator.imagerequest.service;

import com.iaimagegenerator.imagerequest.entity.dto.ImageRequestDTO;
import com.iaimagegenerator.imagerequest.entity.ImageRequested;
import com.iaimagegenerator.imagerequest.infra.kafka.producer.RequestProducer;
import com.iaimagegenerator.imagerequest.repository.ImageRequestedRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImageRequestServiceTest {
    @InjectMocks
    private ImageRequestService service;

    @Mock
    private RequestProducer requestProducer;

    @Mock
    private ImageRequestedRepository imageRequestedRepository;

    @Test
    @DisplayName("shouldRegisterRequest - Deve registrar a requisição")
    void shouldRegisterRequest() {
        // Cria um DTO simulando uma solicitação de geração de imagem
        ImageRequestDTO dto = new ImageRequestDTO(
            UUID.randomUUID(),
            1L,
            "imagem feliz",
            LocalDateTime.now()
        );

        // Simula o salvamento da entidade no banco de dados
        when(imageRequestedRepository.save(any(ImageRequested.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Executa o registro da solicitação de imagem
        service.registerRequest(dto);

        // Verifica se a solicitação foi salva no repositório
        verify(imageRequestedRepository).save(any(ImageRequested.class));

        // Verifica se a mensagem foi enviada para o Kafka
        verify(requestProducer).sendRequest(any(ImageRequestDTO.class));
    }
}