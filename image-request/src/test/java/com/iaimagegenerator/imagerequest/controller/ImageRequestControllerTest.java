package com.iaimagegenerator.imagerequest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iaimagegenerator.imagerequest.entity.dto.ImageRequestDTO;
import com.iaimagegenerator.imagerequest.service.ImageRequestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.UUID;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ImageRequestController.class)
class ImageRequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Cria um ObjectMapper com suporte aos tipos Java 8 de data/hora
    private final ObjectMapper objectMapper = new ObjectMapper()
            .findAndRegisterModules();

    @MockitoBean
    private ImageRequestService imageRequestService;

    @Test
    void shouldRegisterRequest() throws Exception {

        // Cria um DTO simulando uma requisição de geração de imagem
        ImageRequestDTO dto = new ImageRequestDTO(
            UUID.randomUUID(),
            1L,
            "imagem feliz",
            LocalDateTime.now()
        );

        // Simula o retorno do serviço
        when(imageRequestService.registerRequest(dto))
                .thenReturn(dto);

        // Executa uma chamada HTTP POST simulando o endpoint real
        mockMvc.perform(post("/images")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto)))

            // Verifica se retornou HTTP 202 (Accepted)
            .andExpect(status().isAccepted());

        // Confirma que o service foi chamado
        verify(imageRequestService).registerRequest(dto);
    }


    @Test
    void shouldReturnBadRequestWhenPromptIsEmpty() throws Exception {

        // Cria um DTO com prompt vazio para testar a validação @NotBlank
        ImageRequestDTO dto = new ImageRequestDTO(
            UUID.randomUUID(),
            1L,
            "",
            LocalDateTime.now()
        );

        // Executa uma chamada HTTP POST enviando um DTO inválido
        mockMvc.perform(post("/images")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto)))

            // Verifica que a validação retorna HTTP 400
            .andExpect(status().isBadRequest());

        // Verifica que o service não foi executado,
        // pois a validação falhou antes de chegar no Controller
        verify(imageRequestService, never()).registerRequest(any());
    }
}