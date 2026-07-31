package com.iaimagegenerator.imagevalidate.producer;

import com.iaimagegenerator.imagevalidate.dto.ImageRequestDTO;
import com.iaimagegenerator.imagevalidate.infra.kafka.producer.ValidateProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;
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
                LocalDateTime.now()
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
                LocalDateTime.now()
        );

        // Executa o método responsável por enviar a mensagem para a DLQ
        validateProducer.sendToDlq(dto);

        // Verifica se o KafkaTemplate enviou a mensagem para o tópico correto
        verify(kafkaTemplate).send("image-dlq", dto);
    }

}