package com.ia_image_generator.image_generate.consumer;

import com.ia_image_generator.image_generate.dto.ImageRequestDTO;
import com.ia_image_generator.image_generate.infra.kafka.consumer.GenerateConsumer;
import com.ia_image_generator.image_generate.service.ImageGenerateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.UUID;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GenerateConsumerTest {
    @Mock
    private ImageGenerateService imageGenerateService;

    @InjectMocks
    private GenerateConsumer generateConsumer;

    @Test
    void shouldConsumeMessage() {
        // Cria um DTO simulando uma mensagem recebida do Kafka
        ImageRequestDTO dto = new ImageRequestDTO(
                UUID.randomUUID(),
                1L,
                "imagem feliz",
                Instant.now()
        );

        // Executa o método que consome a mensagem
        generateConsumer.consume(dto);

        // Verifica se a mensagem foi encaminhada para o serviço de geração de imagens
        verify(imageGenerateService).process(dto);
    }
}