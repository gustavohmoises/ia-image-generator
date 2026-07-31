package com.iaimagegenerator.imagevalidate.consumer;

import com.iaimagegenerator.imagevalidate.dto.ImageRequestDTO;
import com.iaimagegenerator.imagevalidate.infra.kafka.consumer.ValidateConsumer;
import com.iaimagegenerator.imagevalidate.service.ImageValidateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ValidateConsumerTest {

    @Mock
    private ImageValidateService imageValidateService;

    @InjectMocks
    private ValidateConsumer validateConsumer;

    @Test
    void shouldConsumeMessage() {

        // Cria um DTO simulando uma mensagem recebida do Kafka
        ImageRequestDTO dto = new ImageRequestDTO(
                UUID.randomUUID(),
                1L,
                "imagem feliz",
                LocalDateTime.now()
        );

        // Executa o método que consome a mensagem
        validateConsumer.consume(dto);

        // Verifica se a mensagem foi encaminhada para o serviço de validação
        verify(imageValidateService).process(dto);
    }

}