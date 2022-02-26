package com.example.pautaapi.rest.controller;

import com.example.pautaapi.domain.Pauta;
import com.example.pautaapi.rest.request.PautaRequest;
import com.example.pautaapi.service.PautaService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PautaController.class)
@AutoConfigureMockMvc
public class PautaControllerTest {
    private static String PAUTA_API = "/api/v1/pautas";

    @Autowired
    MockMvc mvc;

    @MockBean
    PautaService service;

    @Test
    public void cadastrarPauta() throws Exception {
        PautaRequest pautaRequest = new PautaRequest("titulo");
        Pauta pauta = Pauta.builder().id("id").titulo("titulo").build();
        when(service.criarPauta(Mockito.any())).thenReturn(pauta);

        String content = new ObjectMapper().writeValueAsString(pautaRequest);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .post(PAUTA_API)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(content);
        mvc.perform(request)
            .andExpect(status().isCreated())
            .andExpect(jsonPath("id").isNotEmpty())
            .andExpect(jsonPath("titulo").value(pautaRequest.getTitulo()));
    }
}
