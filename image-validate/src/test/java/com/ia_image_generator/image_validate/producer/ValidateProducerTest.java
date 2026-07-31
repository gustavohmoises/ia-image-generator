package com.ia_image_generator.image_validate.producer;

import com.ia_image_generator.image_validate.dto.ImageRequestDTO;
import com.ia_image_generator.image_validate.infra.kafka.producer.ValidateProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.Instant;
import java.util.UUID;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ValidateProducerTest {

    @Mock
    private KafkaTemplate<String, ImageRequestDTO> kafkaTemplate;

    @InjectMocks
    private ValidateProducer validateProducer;

    @Test
    void shouldSendApprovedMessage() {

        // Cria um DTO que será enviado ao tópico de aprovados
        ImageRequestDTO dto = new ImageRequestDTO(
                UUID.randomUUID(),
                1L,
                "imagem feliz",
                Instant.now()
        );

        // Executa o método responsável por enviar a mensagem
        validateProducer.sendApproved(dto);

        // Verifica se o KafkaTemplate enviou a mensagem para o tópico correto
        verify(kafkaTemplate).send("image-approved", dto);
    }

    @Test
    void shouldSendDlqMessage() {

        // Cria um DTO que será enviado para o tópico de mensagens rejeitadas (DLQ)
        ImageRequestDTO dto = new ImageRequestDTO(
                UUID.randomUUID(),
                1L,
                "imagem cachaca",
                Instant.now()
        );

        // Executa o método responsável por enviar a mensagem para a DLQ
        validateProducer.sendToDlq(dto);

        // Verifica se o KafkaTemplate enviou a mensagem para o tópico correto
        verify(kafkaTemplate).send("image-dlq", dto);
    }

}