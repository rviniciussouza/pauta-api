package com.example.pautaapi.rest.controller;

import com.example.pautaapi.constants.OpcaoVoto;
import com.example.pautaapi.domain.Pauta;
import com.example.pautaapi.rest.request.PautaRequest;
import com.example.pautaapi.rest.request.SessaoRequest;
import com.example.pautaapi.rest.request.VotoRequest;
import com.example.pautaapi.rest.response.ResultadoResponse;
import com.example.pautaapi.service.PautaService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.DisplayName;
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

import static com.example.pautaapi.stubs.PautaStub.pautaAberta;
import static com.example.pautaapi.stubs.PautaStub.pautaSemSessao;
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
    @DisplayName("Deve cadastrar uma nova pauta")
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

    @Test
    @DisplayName("Deve abrir uma sessão de votação para a pauta")
    public void abrirSessaoVotacao() throws Exception {
        SessaoRequest sessaoRequest = new SessaoRequest(2);
        when(service.abrirSessao(Mockito.any(), Mockito.any())).thenReturn(pautaSemSessao());
        String content = new ObjectMapper().writeValueAsString(sessaoRequest);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .post(PAUTA_API + "/id/abrir")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(content);
        mvc.perform(request)
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve adicionar um novo voto")
    public void adicionarVoto() throws Exception {
        VotoRequest votoRequest = new VotoRequest("idAssociado", "CPF", OpcaoVoto.SIM);
        when(service.adicionarVoto(Mockito.any(), Mockito.any())).thenReturn(pautaAberta());
        String content = new ObjectMapper().writeValueAsString(votoRequest);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .post(PAUTA_API + "/id/votar")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(content);
        mvc.perform(request)
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve obter o resultado da sessão de votos de uma pauta")
    public void obterResultado() throws Exception {
        ResultadoResponse response = new ResultadoResponse();
        when(service.obterResultadoVotacao(Mockito.anyString())).thenReturn(response);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .get(PAUTA_API + "/id/resultado")
            .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request)
            .andExpect(status().isOk());
    }
}
