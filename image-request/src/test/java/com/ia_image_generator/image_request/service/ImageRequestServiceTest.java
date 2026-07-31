package com.ia_image_generator.image_request.service;

import com.ia_image_generator.image_request.dto.ImageRequestDTO;
import com.ia_image_generator.image_request.entity.ImageRequested;
import com.ia_image_generator.image_request.infra.kafka.producer.RequestProducer;
import com.ia_image_generator.image_request.repository.ImageRequestedRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
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
    void shouldRegisterRequest() {
        // Cria um DTO simulando uma solicitação de geração de imagem
        ImageRequestDTO dto = new ImageRequestDTO(
            UUID.randomUUID(),
            1L,
            "imagem feliz",
            Instant.now()
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